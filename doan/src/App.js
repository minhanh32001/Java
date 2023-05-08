import { Route, Routes } from "react-router-dom";
import "./App.css";
import Auth from "./Features/Auth/Auth";
import LoginForm from "./Features/Auth/components/LoginForm/LoginForm";
import RegisterForm from "./Features/Auth/components/RegisterForm/RegisterForm";
import HomePage from "./Features/Home/components/HomePage";
import ListView from "./ListView";
import Footer from "./components/Footer/Footer";
import Header from "./components/Header/Header";
import Sale from "./Features/Sale/Sale";

function App() {
  return (
    <div className="App">
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="login" element={<Auth />} />
        <Route path="register" element={<Auth />} />
        <Route path="sale" element={<Sale />} />

      </Routes>
    </div>
  );  
}

export default App;
