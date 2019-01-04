/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, { Component } from "react";
import { Text, View } from "react-native";
import ReactNativeNetworkApi from "./src/ReactNativeNetworkApi/ReactNativeNetworkApi";

export default class App extends Component {
  constructor(props) {
    super(props);
    this.callReactNativeNetworkApi = this.callReactNativeNetworkApi.bind(this);
  }
  componentDidMount() {
    this.callReactNativeNetworkApi();
  }
  callReactNativeNetworkApi() {
    ReactNativeNetworkApi.request("https://jsonplaceholder.typicode.com/users")
      .then(response => console.log(response))
      .catch(error => console.log(error));
  }
  render() {
    return <View />;
  }
}
