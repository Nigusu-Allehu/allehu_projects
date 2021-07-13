import React from "react";
import { Image, ScrollView, StyleSheet, View, Text } from "react-native";

const cloud = require("../assets/cloud.png");
const Forcast = ({ date, night, day }) => {
  return (
    <View style={styles.forcast_container}>
      <View>
        <Text style={styles.forcast_date}>{date}</Text>
      </View>
      <View>
        <Image source={cloud} style={styles.forcast_image}></Image>
      </View>
      <View forcast_text_container>
        <Text style={styles.forcast_texts}>Night-</Text>
        <Text style={styles.forcast_texts}>{night}</Text>
        <Text style={styles.forcast_texts}>Day-</Text>
        <Text style={styles.forcast_texts}>{day}</Text>
      </View>
    </View>
  );
};
const Scrollable_weather = () => {
  return (
    <View style={styles.scroll_container}>
      <ScrollView style={styles.container} horizontal={true}>
        <View style={styles.today}>
          <View style={styles.left}>
            <Image style={styles.image} source={cloud}></Image>
          </View>
          <View style={styles.right}>
            <Text style={styles.date_today}> Sunday</Text>
            <View style={styles.forcast_text_container}>
              <Text style={styles.today_texts}>Night-</Text>
              <Text style={styles.today_texts}>27.9C</Text>
            </View>
            <View style={styles.forcast_text_container}>
              <Text style={styles.today_texts}>Day-</Text>
              <Text style={styles.today_texts}>12.5</Text>
            </View>
          </View>
        </View>
        <Forcast date={"Tue"} night={"29.3C"} day={"22.3C"} />
        <Forcast date={"Tue"} night={"29.3C"} day={"22.3C"} />
      </ScrollView>
    </View>
  );
};

const styles = StyleSheet.create({
  scroll_container: {
    height: 200,
    margin: 10,
  },
  container: {
    flex: 1,
  },
  left: {
    justifyContent: "space-around",
  },
  right: {
    margin: 15,
    justifyContent: "space-between",
  },
  today: {
    width: "76%",
    backgroundColor: "#18181b99",
    justifyContent: "space-between",
    flexDirection: "row",
    marginHorizontal: 10,
    borderRadius: 12,
    borderWidth: 1,
    borderColor: "white",
  },
  image: {
    height: 50,
    width: 50,
  },
  date_today: {
    backgroundColor: "#17171a98",
    borderRadius: 10,
    paddingHorizontal: 10,
    color: "white",
    fontWeight: "100",
    fontSize: 13,
  },
  today_texts: {
    color: "white",
    fontWeight: "100",
    fontSize: 11,
  },
  forcast_date: {
    marginHorizontal: 30,
    color: "white",
  },
  forcast_texts: { marginHorizontal: 30, color: "white", fontSize: 10 },
  forcast_image: {
    height: 50,
    width: 50,
  },
  forcast_container: {
    backgroundColor: "#17171a98",
    marginHorizontal: 10,
    borderRadius: 12,
    borderWidth: 1,
    borderColor: "white",
    justifyContent: "space-between",
  },
  forcast_text_container: {
    flexDirection: "column",
    justifyContent: "space-between",
  },
});
export default Scrollable_weather;
