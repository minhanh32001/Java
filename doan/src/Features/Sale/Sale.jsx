import React from "react";
import PropTypes from "prop-types";
import { Box, Typography } from "@mui/material";
import { makeStyles } from "@mui/styles";
import TopSlider from "../../utils/Image/Slider.png";
import SecondSlider from "../../utils/Image/secondSlider.png";
import LastSlider from "../../utils/Image/LastSlider.png";
import Image1 from "../../utils/Image/image 25.png";
import Image2 from "../../utils/Image/image 26.png";
import Image3 from "../../utils/Image/1.png";
import Image4 from "../../utils/Image/image 7.png";

Sale.propTypes = {};
const useStyles = makeStyles(() => ({
  root: {},
  Slider: {
    "& img": {
      maxWidth: "100%",
      height: "auto",
    },
    marginTop: "20px",
  },
  saleProduct: {
    display: "flex",
    flexWrap: "wrap",
    justifyContent: "space-around",
    alignContent: "space-between",
  },
  saleItem: {
    backgroundColor: "#ccc",
    width: "40%",
    height: "50%",
    marginBottom: " 2%",
    "& img": {
      width: "100%",
    },
  },
}));
function Sale(props) {
  const classes = useStyles();
  return (
    <Box className={classes.root}>
      <Typography variant="h4">
        Tưng bừng khai trương. Giảm giá cực sốc!
      </Typography>
      <Box className={classes.Slider}>
        <img src={TopSlider} alt="" />
      </Box>
      <Box className={classes.Slider}>
        <Typography variant="h4">Back to school</Typography>
        <img src={SecondSlider} alt="" />
      </Box>
      <Box className={classes.Slider}>
        <Typography variant="h4">Sale sập sàn 12.12</Typography>
        <img src={LastSlider} alt="" />
      </Box>
      <Box className={classes.saleProduct}>
        <Box className={classes.saleItem}>
          <img src={Image1} alt="" />
          <Typography>Bán hàng không lợi nhuận</Typography>
        </Box>
        <Box className={classes.saleItem}>
          <img src={Image2} alt="" />
          <Typography>Ưu đãi cho thành viên</Typography>
        </Box>
        <Box className={classes.saleItem}>
          <img src={Image3} alt="" />
          <Typography>Macbook pro 2021 giảm sốc </Typography>
        </Box>
        <Box className={classes.saleItem}>
          <img src={Image4} alt="" />
          <Typography>iPad giảm giá trong tháng 12 </Typography>
        </Box>
      </Box>
    </Box>
  );
}

export default Sale;
