import React from "react";
import PropTypes from "prop-types";
import { Controller } from "react-hook-form";
import { TextField } from "@mui/material";

InputField.propTypes = {
  form: PropTypes.object.isRequired,
  name: PropTypes.string.isRequired,
  label: PropTypes.string,
  disabled: PropTypes.bool,
};

function InputField(props) {
  const { form, name, label, disabled } = props;
  const {
    formState: { errors, ...formState },
  } = form;
//formState.errors : error của cả state
//fieldState.errors : error của field
  return (
    <Controller
      name={name}
      control={form.control}
      disabled={disabled}
      render={({ field, fieldState, formState }) => (
        <TextField
          {...field}
          {...fieldState}
          {...formState}
          margin="normal"
          variant="outlined"
          label={label}
          fullWidth
          error={!!fieldState.error}
          helperText={fieldState.error?.message}
        />
      )}
    />
  );
}

export default InputField;
