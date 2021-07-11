import React, { useState } from "react";
import {
  TouchableOpacity,
  Platform,
  TextInput,
  StyleSheet,
  Text,
  View,
  KeyboardAvoidingView,
  ScrollView,
} from "react-native";
import Task from "./components/task";
import theme from "./styles/theme.styles.js";
import DateTimePickerModal from "react-native-modal-datetime-picker";
import * as SQLite from "expo-sqlite";

//task database
function openDatabase() {
  if (Platform.OS === "web") {
    return {
      transaction: () => {
        return {
          executeSql: () => {},
        };
      },
    };
  }

  const db = SQLite.openDatabase("taskDb.db");
  return db;
}

const db = openDatabase();

export default function App() {
  const [dateVisible, setVisibility] = useState(false);
  const [date_value, setDate] = useState(new Date());
  const [task, setTask] = useState("");
  const [taskItems, setTaskItems] = useState([[]]);
  const [update, setupdate] = useState(true);
  const months = [
    "Jan",
    "Feb",
    "Mar",
    "Apr",
    "May",
    "Jun",
    "Jul",
    "Aug",
    "Sep",
    "Oct",
    "Nov",
    "Dec",
  ];
  const selected_date =
    "" +
    date_value.getDate() +
    "-" +
    months[date_value.getMonth()] +
    "-" +
    date_value.getFullYear();

  const table_date =
    "D" +
    date_value.getDate() +
    "_" +
    date_value.getMonth() +
    "_" +
    date_value.getFullYear();

  const get_array = () => {
    db.transaction(
      (tx) => {
        tx.executeSql(
          "select * from " + table_date,
          [],
          (tx, results) => {
            var len = results.rows.length;
            var arr = [];
            for (let i = 0; i < len; i++) {
              arr.push([
                results.rows.item(i).value,
                results.rows.item(i).done,
                results.rows.item(i).id,
              ]);
            }
            setTaskItems(arr);
          },
          (er, err) => {}
        );
      },
      (eroor) => {}
    );
  };

  // create the table

  const CreateTaskTable = () => {
    db.transaction((tx) => {
      tx.executeSql(
        "create table if not exists " +
          table_date +
          " (id integer primary key not null, done int, value text);"
      );
    });
  };

  //add task to db
  const add = () => {
    // is text empty?
    var text = task;
    if (text === null || text === "") {
      return false;
    }

    db.transaction((tx) => {
      tx.executeSql(
        "insert into " + table_date + " (done, value) values (0, ?)",
        [text]
      );
      tx.executeSql("select * from " + table_date, [], (_, { rows }) => {
        setTask("");
      });
    }, null);
    setupdate(true);
  };

  const delete_task = (index) => {
    db.transaction(
      (tx) => {
        tx.executeSql("delete from " + table_date + " where id=" + index);
      },
      (tx, er) => {},
      (er) => {}
    );
    get_array();
  };

  const update_done = (index, new_done) => {
    db.transaction(
      (tx) => {
        tx.executeSql(
          "update " +
            table_date +
            " set done=" +
            new_done +
            " where id=" +
            index,
          [],
          null,
          (tx, er) => {}
        );
      },
      (er) => {}
    );
    setupdate(true);
  };
  const showDatePicker = () => {
    setVisibility(true);
  };
  const hideDatePicker = () => {
    setVisibility(false);
  };
  const handleConfirm = (date) => {
    hideDatePicker();
    setDate(date);
    setupdate(true);
  };

  if (update) {
    CreateTaskTable();
    get_array();
    setupdate(false);
  }
  return (
    <View style={styles.container}>
      <View style={styles.title}>
        <Text style={styles.title_text}> Tasks</Text>
        <TouchableOpacity onPress={showDatePicker}>
          <View style={styles.dateWrapper}>
            <Text style={styles.dateText}>{selected_date}</Text>
          </View>
        </TouchableOpacity>
        <DateTimePickerModal
          isVisible={dateVisible}
          mode="date"
          onConfirm={handleConfirm}
          onCancel={hideDatePicker}
          date={date_value}
        />
      </View>
      <ScrollView style={styles.tasks}>
        {taskItems.map((item, index) => {
          return (
            <TouchableOpacity
              key={index}
              onPress={() => update_done(item[2], item[1] == 0 ? 1 : 0)}
              onLongPress={() => {
                delete_task(item[2]);
              }}
            >
              <Task
                text={item[0]}
                color={
                  item[1] == 0 ? theme.PRIMARY_COLOR : theme.SECONDARY_COLOR
                }
              />
            </TouchableOpacity>
          );
        })}
      </ScrollView>

      <KeyboardAvoidingView
        behavior={Platform.OS === "ios" ? "padding" : "height"}
        style={styles.writeWrapper}
      >
        <TextInput
          value={task}
          style={styles.input}
          placeholder={"New Task!"}
          onChangeText={(text) => setTask(text)}
          onSubmitEditing={() => {
            add();
          }}
        ></TextInput>
        <TouchableOpacity
          onPress={() => {
            add();
          }}
        >
          <View style={styles.ButtonWrapper}>
            <Text style={styles.addButton}>+</Text>
          </View>
        </TouchableOpacity>
      </KeyboardAvoidingView>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    backgroundColor: "white",
    alignItems: "flex-start",
    flexDirection: "column",
    height: "100%",
    width: "100%",
    padding: 20,
  },
  title: {
    marginVertical: 33,
    marginTop: 50,
    width: "100%",
    flexDirection: "row",
    justifyContent: "space-between",
  },
  tasks: {
    width: "100%",
    marginVertical: 10,
    marginBottom: 50,
  },
  writeWrapper: {},
  title_text: {
    fontSize: theme.TITLE_FONT_SIZE,
    fontWeight: "bold",
  },

  writeWrapper: {
    position: "absolute",
    bottom: 33,
    width: "100%",
    flexDirection: "row",
    justifyContent: "space-between",
    alignItems: "center",
    marginHorizontal: 20,
  },
  input: {
    paddingHorizontal: 15,
    paddingVertical: 15,
    backgroundColor: "#fff",
    borderRadius: 60,
    borderColor: "#C0C0C0",
    borderWidth: 1,
    width: "80%",
  },
  ButtonWrapper: {
    width: 60,
    height: 60,
    backgroundColor: theme.PRIMARY_COLOR,
    borderRadius: 60,
    justifyContent: "center",
    alignItems: "center",
    borderColor: "#c0c0c0",
    borderWidth: 1,
  },
  addButton: {
    fontWeight: "900",
    color: "white",
  },
  dateWrapper: {},
  dateText: {
    marginTop: 20,
    fontSize: theme.DATE_FONT_SIZE,
  },
});
