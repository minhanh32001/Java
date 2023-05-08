import React from "react";
import PropTypes from "prop-types";
import HomePage from "../../utils/Image/HomePage.png";
import LoginForm from "./components/LoginForm/LoginForm";
import { makeStyles } from "@mui/styles";
import { Box } from "@mui/material";
import { useMatch } from "react-router-dom";
import RegisterForm from "./components/RegisterForm/RegisterForm";

Auth.propTypes = {};
const useStyles = makeStyles(() => ({
  root: {
    display: "flex",
    textAlign:"center",
    width: "100%",
    minHeight: "100vh",
    overflow: "hidden",
    justifyContent: "center",
  },
  left: {
    width: "60%",
    height: "100%",
    maxHeight:"100vh",
    "& img": {
      width: "100%",
      height: "100%",
      
    },
  },
  right: {
    // margin: "10px 50px 0 50px",
    display: "flex",
    justifyContent: "center",
    alignItems: "center",
    position: "relative",
    flex: "1",
  },
}));

function Auth(props) {
  const classes = useStyles();
  const match = useMatch("/login");
  if (match) {
    return (
      <div className={classes.root}>
        <Box className={classes.left}>
          <img src={HomePage} alt="Hình ảnh trang chủ" />
        </Box>
        <Box className={classes.right}>
          <LoginForm />
        </Box>
      </div>
    );
  } else {
    return (
      <div className={classes.root}>
        <Box className={classes.left}>
          <img src={HomePage} alt="Hình ảnh trang chủ" />
        </Box>
        <Box className={classes.right}>
          <RegisterForm />
        </Box>
      </div>
    );
  }
}

export default Auth;
