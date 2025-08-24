import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import App from "./App.jsx";
import "./index.css";
import { CarritoProvider } from "./context/carritoContext.jsx";

createRoot(document.getElementById("root")).render(
  <StrictMode>
    <CarritoProvider>
      <App />
    </CarritoProvider>
  </StrictMode>
);
