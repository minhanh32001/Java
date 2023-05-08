import React from "react";
import PropTypes from "prop-types";
import { makeStyles } from "@mui/styles";
import { useForm } from "react-hook-form";
import * as yup from "yup";
import { yupResolver } from "@hookform/resolvers/yup";
import { Box, Button, LinearProgress, Typography } from "@mui/material";
import InputField from "../../../../components/form-control/inputField/InputField";
import PasswordField from "../../../../components/form-control/passwordField/PasswordField";
import PersonOutlineIcon from "@mui/icons-material/PersonOutline";
import LockIcon from "@mui/icons-material/Lock";
import FacebookIcon from "@mui/icons-material/Facebook";
import EmailIcon from "@mui/icons-material/Email";
import TwitterIcon from "@mui/icons-material/Twitter";

RegisterForm.propTypes = {};
const useStyles = makeStyles(() => ({
  root: {
    padding: "30px 20px",
  },
  header: {
    textAlign: "center",
    marginBottom: "30px",
  },
  social: {
    display: "flex",
    justifyContent: "center",
    margin: "15px 0px",
    gap: "15px",
    "& svg": {
      width: "3.4rem",
      height: "4.5rem",
    },
  },
  socialLogin: {
    marginTop: "30px",
  },
  btn: {
    marginTop: "30px !important",
  },
  copyRight: {
    position: "absolute",
    bottom: "0",
    left: "50%",
    transform: "translateX(-50%)",
    "@media (max-width: 768px)": {
      position: "relative",
      bottom: "auto",
      transform: "none",
      textAlign: "center",
    },
  },
  input: {
    display: "flex",
    justifyContent: "center",
    alignItems: "center",
    "& svg": {
      width: "2.4rem",
      height: "3.5rem",
    },
  },
}));
function RegisterForm(props) {
  const classes = useStyles();
  const schema = yup
    .object({
      identifier: yup
        .string()
        .required("Please enter your email address")
        .email("Please enter a valid email address"),
      password: yup.string().required("Please enter your password"),
    })
    .required();
  const form = useForm({
    defaultValues: {
      identifier: "",
      password: "",
    },
    resolver: yupResolver(schema),
  });
  const handleSubmit = async (values) => {
    const { onSubmit } = props;
    if (onSubmit) {
      await onSubmit(values);
    }
  };
  const { isSubmitting } = form.formState;
  return (
    <div className={classes.root}>
      {isSubmitting && <LinearProgress className={classes.progress} />}
      <Box className={classes.header}>
        <Typography variant="h2">Tạo tài khoản</Typography>
        <Typography>Dễ dàng theo dõi - tiện lợi mua sắm</Typography>
      </Box>
      <Box>
        <form>
          <Box className={classes.input}>
            <PersonOutlineIcon />
            <InputField name="identifier" label="Email" form={form} />
          </Box>
          <Box className={classes.input}>
            <PersonOutlineIcon />
            <InputField name="identifier" label="Email" form={form} />
          </Box>
          <Box className={classes.input}>
            <LockIcon />
            <PasswordField name="password" label="Password" form={form} />
          </Box>
          <Box className={classes.input}>
            <LockIcon />
            <PasswordField
              name="retypePassword"
              label="Retype Password"
              form={form}
            />
          </Box>
          <Button
            disable={isSubmitting}
            className={classes.btn}
            type="submit"
            variant="contained"
            size="large"
          >
            Đăng Nhập
          </Button>
        </form>
      </Box>
      <Box className={classes.socialLogin}>
        <Typography>Hoặc đăng nhập bằng</Typography>
        <Box className={classes.social}>
          <FacebookIcon color="primary" />
          <EmailIcon color="primary" />
          <TwitterIcon color="primary" />
        </Box>
      </Box>
      <Box className={classes.copyRight}>
        <Typography>@Copyright iTech Store</Typography>
      </Box>
    </div>
  );
}

export default RegisterForm;
