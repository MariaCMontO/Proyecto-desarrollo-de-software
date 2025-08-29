import { Link } from "react-router-dom";
import styles from "./Landing.module.css";

export default function Landing() {
  return (
    <div>
      <header>
        <nav className={styles.navegacion}>
          <div className={styles.navegacion_izquierda}>
            <img src="/Logo.png" alt="Logo" className={styles.logo} />
            <div className={styles.navegacion_menu}>
              <p>Inicio</p>
              <p>Nosotros</p>
              <p>Instalaciones</p>
              <p>Contacto</p>
            </div>
          </div>
          <Link to="/cliente">
            <button className={styles.navegacion_boton_realizar_pedido}>
              Realizar Pedido
            </button>
          </Link>
        </nav>
      </header>
      <main>

        <div className={styles.banner}>
          <img src="/fondo_registro.png" alt="Imagen hamburguesa" />
          <div className={styles.banner_texto}>
            <p>DESARROLLAMOS LA FÓRMULA</p>
            <p>A LA NO TE PODRÁS RESISTIR</p>
          </div>

          <section className={styles.seccionNosotros}>
            <div className={styles.parrafo}>
              <div className={styles.fondo}>
                <img src="/fondo_nosotros.jpg" alt="Fondo nosotros" />
              </div>
            </div>
          </section >



        </div>

      </main >
    </div >
  );
}
