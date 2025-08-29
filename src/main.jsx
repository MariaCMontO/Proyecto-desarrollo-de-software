import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import App from "./App.jsx";
import "./index.css";
import { CarritoProvider } from "./context/carritoContext.jsx";
import { HistorialProvider } from "./context/historialContext.jsx";
import { ProductosProvider } from "./context/productosContext.jsx";

createRoot(document.getElementById("root")).render(
  <StrictMode>
    <ProductosProvider>
      <HistorialProvider>
        <CarritoProvider>
          <App />
        </CarritoProvider>
      </HistorialProvider>
    </ProductosProvider>
  </StrictMode>
);
