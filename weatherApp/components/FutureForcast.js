import React from "react";
import { View, Text, StyleSheet, Image } from "react-native";
const img = require("../assets/cloud.png");
const FutureForcast = () => {
  return (
    <View style={{ flexDirection: "row" }}>
      <FFItem />
      <FFItem />
      <FFItem />
      <FFItem />
      <FFItem />
      <FFItem />
      <FFItem />
    </View>
  );
};

const FFItem = () => {
  return (
    <View style={styles.ffitemcontainer}>
      <Text style={styles.day}>Mon</Text>
      <Image source={img} style={styles.image} />
      <Text style={styles.temp}>Night 26</Text>
      <Text style={styles.temp}>Day 36</Text>
    </View>
  );
};
export default FutureForcast;

const styles = StyleSheet.create({
  image: {
    height: 100,
    width: 100,
  },
  ffitemcontainer: {
    flex: 1,
    justifyContent: "center",
    backgroundColor: "#00000033",
    borderRadius: 10,
    borderColor: "#eee",
    borderWidth: 1,
    padding: 20,
    marginLeft: 10,
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
