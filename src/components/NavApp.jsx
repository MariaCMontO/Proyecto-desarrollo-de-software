import { Link } from 'react-router-dom';
import styles from './NavApp.module.css'

export default function NavApp({elementos}) {
  return (
    <>
    <div className={styles.contenedorPrincipal}>
    {elementos.map((elemento) => (
      <Link to={elemento.link} key={elemento.imagen}>
        <div className={styles.contenedor}>
            <img className={styles.imagen} src={elemento.imagen} alt="" />
            <h1 className={styles.texto}>{elemento.nombre}</h1>
        </div>
        </Link>
    )
    )}
    </div>
    </>
  );
}
