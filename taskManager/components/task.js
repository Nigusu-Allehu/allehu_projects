import React from 'react';
import { StyleSheet, View, Text } from 'react-native';
import theme from '../styles/theme.styles.js';

const Task=(props)=>{
  return (
<View style={styles(props.color).task_wrapper}>
  <View style={styles(props.color).box}></View>
  <Text style={styles(props.color).text}>{props.text} </Text>
</View> 
  )
}

const styles=(color)=>StyleSheet.create({
  task_wrapper:{
    backgroundColor:theme.LIGHT_THEME_COLOR,
    padding:15,
    height:45,
    width:'80%',
    flexDirection:'row',
    alignItems:'center',
    marginBottom:25
    
    
  },
  box:{
    backgroundColor:color,
    height:24,
    width:23,
    borderRadius:5,
    marginRight:10,
    
  },
  text:{
    color:theme.FONT_COLOR,
    fontSize:theme.TASK_FONT_SIZE,
  },
})
export default Task;