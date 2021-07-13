import { StatusBar } from "expo-status-bar";
import React from "react";
import { ImageBackground, StyleSheet, Text, View } from "react-native";
import DateTime from "./components/DateTume";
import WeatherScroll from "./components/weatherScroll";
const back_ground = require("./assets/back_ground.jpg");

export default function App() {
  return (
    <View style={styles.container}>
      <ImageBackground source={back_ground} style={styles.img_back}>
        <DateTime />
        <WeatherScroll />
      </ImageBackground>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  img_back: {
    flex: 1,
    resizeMode: "cover",
    justifyContent: "center",
  },
});
