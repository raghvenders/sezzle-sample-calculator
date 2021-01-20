import React from "react";
import Display from "./Display";
import ButtonPanel from "./ButtonPanel";
import calculate from "../logic/calculate";
import axios from 'axios';
import register from "./websocket-listener";
import ListItems from "./ListItems";
import "./App.css";


export default class App extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      items: [],
      currentItem: {
        text: '',
        key: ''
      },
      total: null,
      next: null,
      operation: null,
    };
    this.UpdateCompute = this.UpdateCompute.bind(this);
  }


  handleClick = buttonName => {
    let result = calculate(this.state, buttonName)
    this.setState(result)
    if (buttonName === "=" && result.total) {

      axios.post(`/result`, { total: result.total }, { headers: { 'Content-Type': 'application/json' } })
    }
  };

  UpdateCompute(item) {
    let copyArray = [...this.state.items]
    if (copyArray.length === 10) {
      copyArray.pop()
    }


    const items = [JSON.parse(item.body), ...copyArray]
    this.setState({
      items: items
    })
  }

  componentDidMount() {
    axios.get(`/load`, { headers: { 'Content-Type': 'application/json' } })
      .then(res => {
        if (res.data.length > 0) {
          this.setState({
            items: res.data
          })
        }
      })
    register([
      { route: '/topic/message', callback: this.UpdateCompute }
    ]);
  }

  setUpdate(text, key) {
    console.log("items:" + this.state.items);
    const items = this.state.items;
    items.map(item => {
      if (item.key === key) {
        console.log(item.key + "    " + key)
        item.text = text;
      }
    })
    this.setState({
      items: items
    })
  }

  render() {
    return (
      <div className="component-app">
        <Display value={this.state.next || this.state.total || "0"} />
        <ButtonPanel clickHandler={this.handleClick} />
        <ListItems items={this.state.items} setUpdate={this.setUpdate} />
      </div>
    );
  }
}
