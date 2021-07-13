import { ScrollView, StyleSheet, View, Text, Image } from "react-native";
import React from "react";
import FutureForcast from "./FutureForcast";
const img = require("../assets/cloud.png");

const Today_temp = () => {
  return (
    <View style={styles.today_container}>
      <Image source={img} style={styles.img} />
      <View style={styles.today_values}>
        <Text style={styles.day}>Sunday </Text>
        <Text style={styles.temp}> Night </Text>
        <Text style={styles.temp}> Day</Text>
      </View>
    </View>
  );
};
export default function WeatherScroll() {
  return (
    <ScrollView style={styles.scrolleView} horizontal={true}>
      <Today_temp />
      <FutureForcast />
    </ScrollView>
  );
}
const styles = StyleSheet.create({
  img: {
    height: 150,
    width: 150,
  },
  scrolleView: {
    flex: 0.3,
    backgroundColor: "#18181b44",
    padding: 30,
  },
  today_container: {
    flexDirection: "row",
    backgroundColor: "#00000033",
    justifyContent: "center",
    alignItems: "center",
    borderRadius: 10,
    borderColor: "#eee",
    borderWidth: 1,
    padding: 15,
  },
  today_values: {
    paddingRight: 40,
  },
  day: {
    fontSize: 20,
    color: "white",
    backgroundColor: "#3c3c44",
    padding: 10,
    textAlign: "center",
    borderRadius: 50,
    fontWeight: "200",
    marginBottom: 15,
  },
  temp: {
    fontSize: 16,
    color: "white",
    fontWeight: "100",
    textAlign: "center",
  },
});
