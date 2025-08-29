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
            <div className={styles.seccionNosotros_texto}>
              <p>En FoodLab creemos que la buena comida no solo se cocina… ¡se formula! Somos un restaurante
                de comida rápida donde cada bocado es el resultado de una receta precisa: sabor, calidad y rapidez
                en la medida perfecta.</p>

              <p>Inspirados en la creatividad de un laboratorio, combinamos ingredientes frescos,
                técnicas innovadoras y un toque de locura culinaria para sorprenderte en cada visita.
                Nuestro objetivo es simple: que disfrutes de tu comida favorita con la fórmula que siempre funciona — rápida,
                deliciosa y con un estilo único.</p>

            </div>
            <div className={styles.seccionNosotros_imagen}>
            <img src="/fondo_nosotros.jpg" alt="Fondo nosotros" />
            </div>
          </section >



        </div>

      </main >
    </div >
  );
}
