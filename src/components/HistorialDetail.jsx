import styles from "./HistorialDetail.module.css";
import CartDetail from "./CartDetail";
import { useHelpers } from "../hooks/useHelpers";

export default function HistorialDetail({ orden }) {

  const total = orden.productos.reduce((total, producto) => {
    return total + producto.precio * producto.cantidad;
  }, 0);

  const {formatoCOP}= useHelpers()
  return (
    <div className={styles.contenedor}>
      <div className={styles.contenedorRow}>
        <p className={styles.parrafos}>Orden:</p>
        <p className={styles.parrafos}>{orden.id}</p>
      </div>
      <div className={styles.contenedorRow}>
        <p className={styles.parrafos}>Cliente:</p>
        <p className={styles.parrafos}>{orden.cliente}</p>
      </div>
      <div>
        <div className={styles.contenedorProductos}>
          {orden.productos.map((producto) => (
            <CartDetail producto={producto} historial={true} />
          ))}
        </div>
      </div>
      <div className={styles.contenedorFinal}>
        <div className={styles.contenedorRow}>
        <p className={styles.parrafos}>Fecha:</p>
        <p className={styles.parrafos}>{orden.fecha}</p>
      </div>
      <div className={styles.contenedorTotal}>
        <p className={styles.parrafosRojos}>Total:</p>
        <p className={styles.parrafosRojos}>{formatoCOP.format(total)}</p>
      </div>
      </div>
    </div>
  );
}
