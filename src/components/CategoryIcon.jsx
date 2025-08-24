import styles from "./CategoryIcon.module.css";

export default function CategoryIcon({categoria, setCategory, selected}) {
  return (
    <div className={`${styles.contenedorPrincipal} ${selected&& styles.activo}`} onClick={() => setCategory(categoria.nombre)}>
      <div className={styles.contenedor}>
        <img className={styles.imagen} src={categoria.imagen} alt="" />
        <p className={styles.texto}>{categoria.nombre}</p>
      </div>
    </div>
  );
}
