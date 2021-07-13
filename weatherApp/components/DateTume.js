import React from "react";
import { View, Text, StyleSheet } from "react-native";

const WeatherItem = ({ title, value, unit }) => {
  return (
    <View style={styles.weatheritem}>
      <Text style={styles.weatheritemTitle}>{title}</Text>
      <Text style={styles.weatheritemTitle}>
        {value}
        {unit}
      </Text>
    </View>
  );
};

const DateTime = () => {
  return (
    <View style={styles.container}>
      <View style={styles.left}>
        <View style={styles.time}>
          <Text style={styles.time_text}>00:34</Text>
        </View>
        <View style={styles.date}>
          <Text style={styles.date_text}>Monday, 7 Jun</Text>
        </View>
        <View style={styles.weather}>
          <WeatherItem title="Humidity" value="79" unit="%" />
          <WeatherItem title="Pressure" value="709" unit="hPA" />
          <WeatherItem title="Sunrise" value="9" unit="AM" />
          <WeatherItem title="Sunset" value="9" unit="PM" />
        </View>
      </View>

      <View style={styles.right}>
        <View>
          <Text style={styles.location}>Ithaca, NY</Text>
        </View>
        <View>
          <Text style={styles.lat_long}>1.2 2.4 E</Text>
        </View>
      </View>
    </View>
  );
};
const styles = StyleSheet.create({
  container: {
    padding: 15,
    flex: 1.5,
    flexDirection: "row",
    justifyContent: "space-between",
  },
  time_text: {
    fontSize: 45,
    color: "white",
    fontWeight: "100",
  },
  left: {},
  time: {},
  date_text: {
    fontSize: 25,
    color: "#eee",
    fontWeight: "100",
  },
  weather: {
    backgroundColor: "#18181b99",
    borderRadius: 20,
    padding: 10,
    marginTop: 10,
    borderColor: "white",
    borderWidth: 1,
  },
  right: {
    textAlign: "right",
  },
  location: {
    fontSize: 20,
    color: "white",
    fontWeight: "100",
  },
  lat_long: {
    fontSize: 16,
    color: "white",
    fontWeight: "400",
  },
  weatheritem: {
    flexDirection: "row",
    justifyContent: "space-between",
  },
  weatheritemTitle: {
    color: "#eee",
    fontSize: 14,
    fontWeight: "100",
  },
});
export default DateTime;
