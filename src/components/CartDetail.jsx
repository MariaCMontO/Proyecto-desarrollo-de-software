import { useCarritoContext } from "../context/carritoContext";
import { useHelpers } from "../hooks/useHelpers";
import styles from "./CartDetail.module.css";

export default function CartDetail({ producto, setNotaModal, notaModal }) {
  const { formatoCOP } = useHelpers();
  const { dispatch } = useCarritoContext();

  return (
    <div className={styles.container}>
      <div className={styles.contenedorImagen}>
        <img className={styles.image} src={producto.imagen} alt="" />
      </div>
      <div className={styles.contenedorColumn}>
        <div className={styles.contenedorRow}>
          <p className={styles.nombre}>{producto.nombre}</p>
          <button
            onClick={() =>
              dispatch({ type: "Eliminar producto", payload: { producto } })
            }
            className={styles.closeBoton}
          >
            <img className={styles.closeImagen} src="/close_icon.png" alt="" />
          </button>
        </div>
        <p className={styles.precio}>{formatoCOP.format(producto.precio)}</p>
        <div className={styles.contenedorRow}>
          <div className={styles.modificarBoton}>
            <button
              onClick={() =>
                dispatch({ type: "Disminuir cantidad", payload: { producto } })
              }
              className={styles.botones}
            >
              -
            </button>
            <p className={styles.numero}>{producto.cantidad}</p>
            <button
              onClick={() =>
                dispatch({ type: "Añadir cantidad", payload: { producto } })
              }
              className={styles.botones}
            >
              +
            </button>
          </div>
          <p className={styles.subtotal}>
            Subtotal:
            <span>
              {formatoCOP.format(producto.precio * producto.cantidad)}
            </span>
          </p>
          {producto.nota != "" ? (
            <div className={styles.contenedorNota}>
              <p className={styles.nota}>{producto.nota}</p>
              <button className={styles.editarBoton} onClick={() => setNotaModal(producto)}>
                <img className={styles.editarImagen} src="/editar_icon.png" alt="" />
              </button>
            </div>
          ) : (
            <button
              className={styles.agregarBoton}
              onClick={() => setNotaModal(producto)}
            >
              Agregar nota
            </button>
          )}
        </div>
      </div>
    </div>
  );
}
