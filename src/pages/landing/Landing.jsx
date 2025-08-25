import { Link } from "react-router-dom";
import styles from './Landing.module.css';

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
          <button className={styles.navegacion_boton_realizar_pedido}>
            Realizar Pedido
          </button>
        </nav>
      </header>
      <main>
        <div className={styles.banner}>
          <img src="/fondo_registro.png" alt="Imagen hamburguesa" />
          <div className={styles.banner_texto}>
            <p>DESARROLLAMOS LA FÓRMULA</p>
            <p>A LA NO TE PODRÁS RESISTIR</p>
          </div>
        </div>
      </main>

      <Link to="/cliente">
        <button>Realizar pedido</button>
      </Link>

      <Link to={"/registro"}>
        <button>Registro</button>
      </Link>
    </div>
  );
}
