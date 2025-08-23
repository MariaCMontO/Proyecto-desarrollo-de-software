import styles from "./CategoryIcon.module.css";

export default function CategoryIcon({categoria}) {
  return (
    <div className={styles.contenedorPrincipal}>
      <div className={styles.contenedor}>
        <img className={styles.imagen} src={categoria.imagen} alt="" />
        <p className={styles.texto}>{categoria.nombre}</p>
      </div>
    </div>
  );
}
