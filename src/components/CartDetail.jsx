import styles from './CartDetail.module.css'

export default function CartDetail({imagen, nombre, precio}) {
  return (
    <div className={styles.container}>
        <div className={styles.contenedorImagen}>
        <img className={styles.image} src={imagen} alt="" />
        </div>
        <div className={styles.contenedorColumn} >
            <div className={styles.contenedorRow}>
            <p className={styles.nombre}>{nombre}</p>
            <button className={styles.closeBoton} >
                <img className={styles.closeImagen} src="/close_icon.png" alt="" />
            </button>
            </div>
            <p className={styles.precio}>{precio}</p>
            <div className={styles.contenedorRow}>
                <div className={styles.modificarBoton}>
                    <button className={styles.botones}>-</button>
                    <p  className={styles.numero}>1</p>
                    <button className={styles.botones}>+</button>
                </div>
                <p className={styles.subtotal}>Subtotal:<span>${precio}</span></p>
                <button className={styles.agregarBoton}>Agregar nota</button>
            </div>
        </div>
    </div>
  )
}
