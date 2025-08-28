import { Link } from "react-router-dom";
import styles from './Login.module.css'

export default function Login() {
  return (

    <div className={styles.contenedor}>

      <Link to="/">
        <button className={styles.back}>Regresar</button>
      </Link>

      <div>
        <img className={styles.vanished} src="/fondo_login.png" alt="Fondo" />
      </div>

      <section className={styles.login}>
        <div className={styles.imagenes}>
          <img className={styles.logo} src="/Logo.png" alt="Logo" />
          <img className={styles.hamburguesa2} src="/fondo_login.png" alt="Fondo2" />
        </div>

        <aside className={styles.formulario}>
          <h2>Iniciar Sesión</h2>
          <form>
            <label htmlFor="email">Email: </label>
            <input type="text" placeholder="correo@gmail.com" />

            <label htmlFor="contraseña">Contraseña: </label>
            <input type="password" placeholder="***********" />

            <span className={styles.reset}>Olvidaste tu contraseña?</span>

            <input className={styles.ingresar} type="submit" value="INGRESAR" />
            <Link to ='/registro'>
              <span className={styles.registrate}>No tienes cuenta aún? <b>Regístrate aquí</b></span>
            </Link>
          </form>
        </aside>
      </section>
    </div>

  )
}
