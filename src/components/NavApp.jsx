import styles from './NavApp.module.css'

export default function NavApp({elementos}) {
  return (
    <>
    <div className={styles.contenedorPrincipal}>
    {elementos.map((elemento) => (
        <div className={styles.contenedor} key={elemento.imagen} >
            <img className={styles.imagen} src={elemento.imagen} alt="" />
            <h1 className={styles.texto}>{elemento.nombre}</h1>
        </div>
    )
    )}
    </div>
    </>
  );
}
