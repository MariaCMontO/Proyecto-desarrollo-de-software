import styles from "./iaPortal.module.css";

export default function IaPortal() {
  return (
    <>
      <div className={styles.contenedor}>
        <p>ESTAS A UN PASO DE GENERAR TU PEDIDO CON IA</p>
        <button className={styles.boton}>IR A PAGAR</button>
      </div>
    </>
  );
}
