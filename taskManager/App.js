import React,{useState} from 'react';
import { TouchableOpacity, Platform, TextInput, StyleSheet, Text, View,KeyboardAvoidingView, Button } from 'react-native';
import Task from './components/task';
import theme from './styles/theme.styles.js';
import DateTimePickerModal from "react-native-modal-datetime-picker";



export default function App() {
  const [dateVisible, setVisibility] = useState(false);
  const [date_value, setDate]= useState(new Date());
  const [month, setMonth]=useState(date_value.getMonth());
  const [year, setYear]=useState(date_value.getFullYear());
  const [day, setDay]=useState(date_value.getDate());
  const showDatePicker= ()=>{
    setVisibility(true);
  }
  const hideDatePicker= ()=>{
    setVisibility(false)
  }
  const  handleConfirm = (date)=>{
    setDate(date);
    setMonth(date_value.getMonth());
    setYear(date_value.getFullYear());
    setDay(date_value.getDate());
    hideDatePicker();
  }
  const get_date_string =()=>{
    return ""+day+"-"+month+"-"+year
  }
  return (
    <View style={styles.container}>
      <View style={styles.title}>
            <Text style={styles.title_text}> Tasks</Text>
            <TouchableOpacity onPress={showDatePicker} >
                <View style={styles.dateWrapper}>
                    <Text style={styles.dateText}>{""+day+"-"+month+"-"+year}</Text>
                </View>
            </TouchableOpacity>
            <DateTimePickerModal
            isVisible={dateVisible}
            mode="date"
            onConfirm={handleConfirm}
            onCancel={hideDatePicker}
            />
      </View>
      <View style={styles.tasks}>
            <Task color={theme.PRIMARY_COLOR} text={'TASK1'}/>
            <Task color={theme.PRIMARY_COLOR} text={'TASK2 is studying'}/>
            <Task color={theme.PRIMARY_COLOR} text={'TASK1 is playing asoccer is good thing so'}/>
      </View>
      
      <KeyboardAvoidingView 
      behavior={Platform.OS === "ios" ? "padding" :"height" }
      style={styles.writeWrapper}>
            <TextInput style={styles.input} placeholder={'New Task!'} ></TextInput>
            <TouchableOpacity  >
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
    backgroundColor: theme.LIGHT_THEME_COLOR,
    alignItems: 'flex-start',
    flexDirection:'column',
    height:'100%',
    width:'100%',
    padding:20

  },
  title:{
    marginVertical:33,
    marginTop:50,
    width:'100%',
    flexDirection:'row',
    justifyContent:'space-between',
  },
  tasks:{
    width:'100%',
    marginVertical:10,

  },
  writeWrapper:{

},
  title_text:{
    fontSize:theme.TITLE_FONT_SIZE,
    fontWeight:'bold'
    

},
  date:{
    marginRight:10,
    fontWeight:'100'


},
writeWrapper:{
  position:'absolute',
  bottom:33,
  width:'100%',
  flexDirection:'row',
  justifyContent:'space-between',
  alignItems:'center',
  marginHorizontal:20
},
input:{
  paddingHorizontal:15,
  paddingVertical:15,
  backgroundColor:'#fff',
  borderRadius:60,
  borderColor:'#C0C0C0',
  borderWidth:1,
  width: '80%',

},
ButtonWrapper:{
  width:60,
  height:60,
  backgroundColor:theme.PRIMARY_COLOR,
  borderRadius:60,
  justifyContent:'center',
  alignItems:'center',
  borderColor:'#c0c0c0',
  borderWidth:1
},
addButton:{
  fontWeight:'900',
  color:'white'
},
dateWrapper:{},
dateText:{},
});
