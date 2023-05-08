import React from "react";
import PropTypes from "prop-types";
import { Box, TextField, Typography } from "@mui/material";
import { makeStyles } from "@mui/styles";
import logo from "../../utils/Image/logo.png";
import payment from "../../utils/Image/payment.png";
import confirm from "../../utils/Image/confirm.png";

Footer.propTypes = {};
const useStyles = makeStyles(() => ({
  root: {
    width: "100%",
    height: "280px",
    backgroundColor: "#181818",
    textAlign: "left",
    "& .MuiTypography-root": {
      fontSize: "0.8rem",
    },
  },
  container: {
    padding: "20px 0 20px 0",
    display: "flex",
    width: "100%",
    justifyContent: "space-around",
  },
  about: {
    display: "flex",
    marginBottom: "4px",
    color: "#FFFFFF",
    "& .MuiTypography-root": {
      fontWeight: "bold",
    },
    justifyContent: "center",
    alignItems: "center",
  },
  license: {
    display: "flex",
    color: "#FFFFFF",
    flexDirection: "column",
    alignItems: "center",
  },
  header: {
    color: "#FFFFFF",
    fontSize: "1rem !important",
    fontWeight: "bold !important",
    padding: "18px 0",
  },
  content: {
    color: "#FFFFFF",
  },
  subheader: {
    color: "#FF8787",
  },
  input: {
    "& .MuiInputBase-input": {
      color: "#FFFFFF",
      backgroundColor: "#C4C4C4",
      fontSize: "0.8rem",
      borderRadius: "10px",
      width: "100%",
      height: "5px",
    },
  },
}));

function Footer(props) {
  const classes = useStyles();
  return (
    <Box className={classes.root}>
      <Box className={classes.container}>
        <Box className="contact">
          <Box className={classes.about}>
            <img src={logo} alt="LogoShop" />
            <Box className={classes.text}>
              <Typography>i-Tech Store</Typography>
              <Typography className={classes.text_content}>
                Apple Authorised Reseller
              </Typography>
            </Box>
          </Box>
          <Box className={classes.content}>
            <Typography className={classes.subheader}>
              Tư vấn mua hàng (miễn phí)
            </Typography>
            <Typography>190031201</Typography>
            <Typography className={classes.subheader}>
              Hỗ trợ kỹ thuật (miễn phí)
            </Typography>
            <Typography>190031201</Typography>
            <Typography className={classes.subheader}>
              Góp ý, khiếu nại:
            </Typography>
            <Typography>190031201</Typography>
          </Box>
        </Box>
        <Box className="info">
          <Typography className={classes.header}>Thông tin </Typography>
          <Box className={classes.content}>
            <Typography>Giới thiệu về công ty</Typography>
            <Typography>Câu hỏi thường gặp mua hàng</Typography>
            <Typography>Chính sách bảo mật</Typography>
            <Typography>Kiểm tra hóa đơn điện tử</Typography>
            <Typography>Tra cứu thông tin bảo hành</Typography>
          </Box>
        </Box>
        <Box className="policy">
          <Typography className={classes.header}>Chính sách</Typography>
          <Box className={classes.content}>
            <Typography>Chính sách đổi trả</Typography>
            <Typography>Chính sách bảo mật</Typography>
            <Typography>Chính sách trả góp</Typography>
            <Typography>Chính sách bảo hành</Typography>
            <Typography>Chính sách hoạt động chung</Typography>
          </Box>
        </Box>
        <Box className={classes.license}>
          <img src={confirm} alt="Xác nhận của bộ công thương" />
          <Typography>Hỗ trợ thanh toán</Typography>

          <img src={payment} alt="Phương thức thanh toán" />
          <Typography>Đăng kí nhận thông tin</Typography>
          <TextField
            className={classes.input}
            placeholder="Nhập email..."
          ></TextField>
        </Box>
      </Box>
    </Box>
  );
}

export default Footer;
