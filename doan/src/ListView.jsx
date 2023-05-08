import React, { useState } from "react";
import PropTypes from "prop-types";
import PullToRefresh from "react-pull-to-refresh";
import RefreshIcon from "@mui/icons-material/Refresh";
import { Box } from "@mui/material";

ListView.propTypes = {};

function ListView(props) {
  const [refreshing, setRefreshing] = useState(false);
  const [list, setList] = useState(["iPhone", "iPad", "Mac"]);
  const [pullChange, setPullChange] = useState();

  const handleRefresh = async () => {
    setRefreshing(true);
    setTimeout(() => {
      setList(["iPhone", "iPad", "Mac", "AppleWatch"]);
      setRefreshing(false);
    }, 1000);
  };

  return (
    <PullToRefresh onRefresh={handleRefresh} refreshing={refreshing}>
      {refreshing && <RefreshIcon />}
      <ul>
        {list.map((item, index) => (
          <li style={{ listStyleType: "none" }} key={index}>
            {item}{" "}
          </li>
        ))}
      </ul>
    </PullToRefresh>
  );
}

export default ListView;
