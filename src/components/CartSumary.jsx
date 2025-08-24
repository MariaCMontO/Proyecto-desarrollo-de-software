import { useCarritoContext } from "../context/carritoContext";
import { useHelpers } from "../hooks/useHelpers";
import styles from "./CartSumary.module.css";

export default function CartSumary() {

  const {formatoCOP}=useHelpers()
  const { state } = useCarritoContext();
  const total = state.carrito.reduce((total, producto) => {
    return total + producto.precio * producto.cantidad;
  }, 0);

  return (
    <div className={styles.contenedor}>
      <div className={styles.contenedorFlex}>
        <p className={styles.texto}>Subtotal:</p>
        <p className={styles.texto}>{formatoCOP.format(total)}</p>
      </div>
      <div className={styles.contenedorFlex}>
        <p className={styles.texto}>Propina(10%):</p>
        <p className={styles.texto}>{formatoCOP.format(total * 0.1)}</p>
      </div>
      <hr />
      <div className={styles.contenedorFlex}>
        <p className={styles.textoRojo}>Total:</p>
        <p className={styles.textoRojo}>{formatoCOP.format(total * 1.1)}</p>
      </div>
      <button className={styles.boton}>PAGAR</button>
    </div>
  );
}
