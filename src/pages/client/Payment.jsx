import { Link, useLocation } from "react-router-dom";
import { useCarritoContext } from "../../context/carritoContext";
import { useHistorialContext } from "../../context/historialContext";
import Styles from "./Payment.module.css";
import CartDetail from "../../components/CartDetail";
import CartSumary from "../../components/CartSumary";

export default function Payment() {
  const { state: stateCarrito, dispatch: carritoDispatch } =
    useCarritoContext();
  const { state: stateHistorial, dispatch: historialDispatch } =
    useHistorialContext();

  const productos = stateCarrito.carrito;

  const location = useLocation();
  const { ia } = location.state;

  const submit = () => {
    historialDispatch({
      type: "Agregar al historial",
      payload: { productos, cliente: "Juan" },
    });
    carritoDispatch({ type: "Vaciar carrito" });
  };
  return (
    <div className={Styles.cuadroPrincipal}>
      <div className={Styles.cuadroSecundario}>
        <img className={Styles.Logo} src="/Logo.png" alt="" />
        {ia ? (
          <h1 className={Styles.titulo}>¡LA IA HA GENERADO TU ORDEN!</h1>
        ) : (
          <h1 className={Styles.titulo}>¡YA CASI ESTA LISTA TU ORDEN!</h1>
        )}

        {/* Primer cuadro donde esta la primera imagen de hamburguesa y sus datos */}
        <div className={Styles.primerCuadro}>
          {stateCarrito.carrito.map((producto) => (
            <CartDetail
              producto={producto}
              historial={true}
              key={producto.id}
            />
          ))}
        </div>
        <div className={Styles.contenedorSummary}>
          <CartSumary recibo={true} />
        </div>
        {/* Cuarto cuadro donde esta el input del metodo de pago */}
        <div className={Styles.cuartoCuadro}>
          <label htmlFor="metodo" className={Styles.texto}>
            Metodo de pago
          </label>
          <input
            className={Styles.inputUno}
            type="text"
            id="metodo"
            name="metodo"
          />
        </div>
        {/* Quinto cuadro donde estan los datos de la tarjeta */}
        <div className={Styles.quintoCuadro}>
          <p className={Styles.texto}>Numero de tarjeta:</p>
          <input className={Styles.inputDos} type="number" />
          <p className={Styles.texto}>Nombre de la tarjeta:</p>
          <input className={Styles.inputTres} type="text" />
          <p className={Styles.texto}>CVV:</p>
          <input className={Styles.inputCuatro} type="text" />
        </div>
        {/* Boton para confirmar la compra */}
        <Link to="/cliente" className={Styles.boton}>
          <button className={Styles.boton} onClick={submit}>
            FINALIZAR COMPRA
          </button>
        </Link>
      </div>
    </div>
  );
}
