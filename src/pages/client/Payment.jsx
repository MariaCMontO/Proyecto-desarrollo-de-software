import { Link } from "react-router-dom";
import { useCarritoContext } from "../../context/carritoContext";
import { useHistorialContext } from "../../context/historialContext";
import styles from "./Payment.module.css";

export default function Payment() {
  const { state: stateCarrito, dispatch: carritoDispatch } =
    useCarritoContext();
  const { state: stateHistorial, dispatch: historialDispatch } =
    useHistorialContext();

  const productos = stateCarrito.carrito;

  const submit = () => {
    historialDispatch({
      type: "Agregar al historial",
      payload: { productos, cliente: "Juan" },
    });
    carritoDispatch({ type: "Vaciar carrito" });
  };
  return (
    <>
    <Link to="/cliente">
      <button className={styles.boton} onClick={submit}>
        FINALIZAR COMPRA
      </button>
      </Link>
    </>
  );
}
