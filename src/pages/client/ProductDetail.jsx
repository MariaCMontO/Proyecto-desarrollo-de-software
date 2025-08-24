import { useCarritoContext } from '../../context/carritoContext'
import styles from './ProductDetail.module.css'


export default function ProductDetail({producto}) {

  const{dispatch}=useCarritoContext()

  return (
    <div className={styles.contenedor}>
        <div className={styles.contenedorImagen}>
        <img className={styles.imagen} src={producto.imagen} alt="" />
        </div>
        <p className={styles.nombre}>{producto.nombre}</p>
        <p className={styles.precio}>${producto.precio}</p>
        <button onClick={() => dispatch({type:"Añadir al carrito", payload:{producto}})} className={styles.boton}>AGREGAR</button>
    </div>
  )
}
