import styles from "./CartSumary.module.css";

export default function CartSumary() {
  return (
    <div className={styles.contenedor}>
      <div className={styles.contenedorFlex}>
        <p className={styles.texto}>Subtotal:</p>
        <p className={styles.texto}>$90.000</p>
      </div>
      <div className={styles.contenedorFlex}>
        <p className={styles.texto}>Propina(10%):</p>
        <p className={styles.texto}>$9.000</p>
      </div>
      <hr />
      <div className={styles.contenedorFlex}>
        <p className={styles.textoRojo}>Total:</p>
        <p className={styles.textoRojo}>$99.000</p>
      </div>
      <button className={styles.boton}>PAGAR</button>
    </div>
  );
}
