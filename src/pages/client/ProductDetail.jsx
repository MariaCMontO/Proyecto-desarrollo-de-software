import styles from './ProductDetail.module.css'

export default function ProductDetail({imagen, nombre, precio}) {
  return (
    <div className={styles.contenedor}>
        <div className={styles.contenedorImagen}>
        <img className={styles.imagen} src={imagen} alt="" />
        </div>
        <p className={styles.nombre}>{nombre}</p>
        <p className={styles.precio}>${precio}</p>
        <button className={styles.boton}>AGREGAR</button>
    </div>
  )
}
