import React, { useState } from "react";
import PropTypes from "prop-types";
import { useNavigate } from "react-router-dom";
import { Box, Button, Icon, Typography } from "@mui/material";
import { makeStyles } from "@mui/styles";
import LocationOnIcon from "@mui/icons-material/LocationOn";
import logo from "../../utils/Image/logo.png";
import HelpOutlineIcon from "@mui/icons-material/HelpOutline";
import ShoppingCartIcon from "@mui/icons-material/ShoppingCart";
import SearchIcon from "@mui/icons-material/Search";
import { styled, alpha } from "@mui/material/styles";
import InputBase from "@mui/material/InputBase";

Header.propTypes = {};
const useStyles = makeStyles(() => ({
  root: {
    width: "100%",
    height: "100px",
  },
  header: {
    display: "flex",
    alignItems: "center",
    padding: "0 20px",
    justifyContent: "space-between",
  },
  left: { display: "flex", alignItems: "center", gap: "40px" },
  about: {
    display: "flex",
    alignItems: "center",
  },
  text: {
    textAlign: "left",
    "& .MuiTypography-root": {
      fontWeight: "bold",
    },
  },
  right: {
    display: "flex",
    alignItems: "center",
    gap: "15px",
  },
  navigate: {
    display: "flex",
    justifyContent: "space-around",
    backgroundColor: "#181818",
    padding: "0 200px",
    "& .MuiButtonBase-root": {
      color: "#FFFFFF !important",
      textTransform: "capitalize",
    },
  },
  text_content: {
    fontSize: "0.5rem !important", // sử dụng con của text đi
  },
}));

const Search = styled("div")(({ theme }) => ({
  position: "relative",
  borderRadius: "15px",
  backgroundColor: "#E5E5E5",
  "&:hover": {
    backgroundColor: alpha(theme.palette.common.white, 0.25),
  },
  width: "100%",
  [theme.breakpoints.up("sm")]: {
    marginLeft: theme.spacing(3),
    width: "auto",
  },
}));

const SearchIconWrapper = styled("div")(({ theme }) => ({
  height: "100%",
  position: "absolute",
  pointerEvents: "none",
  display: "flex",
  alignItems: "center",
  justifyContent: "center",
  right: "7px",
  top: "0",
  color: "#8F8F8F",
}));

const StyledInputBase = styled(InputBase)(({ theme }) => ({
  color: "inherit",
  "& .MuiInputBase-input": {
    padding: "10px",
    fontSize: "0.8rem",
    // vertical padding + font size from searchIcon
    paddingRight: `calc(1em + ${theme.spacing(4)})`,
    transition: theme.transitions.create("width"),
    width: "100%",
    [theme.breakpoints.up("md")]: {
      width: "20ch",
    },
  },
}));

function Header(props) {
  const classes = useStyles();
  const navigate = useNavigate();
  // const handleLogIn = () => {
  //   setStatus("login", () => {
  //     navigate(`/${status}`);
  //   });
  // };
  const handleLogIn = () => {
    navigate("/login");
  };
  const handleRegister = () => {
    navigate("/register");
  };
  return (
    <Box className={classes.root}>
      <Box className={classes.header}>
        <Box className={classes.left}>
          <Box className={classes.about}>
            <img src={logo} alt="LogoShop" />
            <Box className={classes.text}>
              <Typography>i-Tech Store</Typography>
              <Typography className={classes.text_content}>
                Apple Authorised Reseller
              </Typography>
            </Box>
          </Box>
          <Box className="search">
            <Search>
              <StyledInputBase
                placeholder="Tìm kiếm mọi sản phẩm "
                inputProps={{ "aria-label": "search" }}
              />
              <SearchIconWrapper>
                <SearchIcon />
              </SearchIconWrapper>
            </Search>
          </Box>
        </Box>
        <Box className={classes.right}>
          <LocationOnIcon />
          <HelpOutlineIcon />
          <ShoppingCartIcon />
          <Button onClick={handleLogIn}>Đăng Nhập</Button>
          <Button onClick={handleRegister}>Đăng Ký</Button>
        </Box>
      </Box>
      <Box className={classes.navigate}>
        <Button>Khuyến mãi </Button>
        <Button>Mac</Button>
        <Button>iPad</Button>
        <Button>iPhone</Button>
        <Button>Watch</Button>
        <Button>Airpods</Button>
        <Button>Phụ kiện</Button>
      </Box>
    </Box>
  );
}

export default Header;
