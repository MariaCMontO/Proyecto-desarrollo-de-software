import { Link } from "react-router-dom";
import styles from "./Landing.module.css";

export default function Landing() {
  return (
    <>
      <h1 className={styles.titulo}>Aqui va el LANDING PAGE de SANTIAGO</h1>
      
      <Link to="/cliente">
        <button>Realizar pedido</button>
      </Link>

      <Link to={"/registro"}>
      <button>Registro</button>
      </Link>
    </>
  );
}
