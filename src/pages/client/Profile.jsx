import { useState } from "react";
import styles from "./Profile.module.css";
import NavApp from "../../components/NavApp";
import CategoryIcon from "../../components/CategoryIcon";
import { Link, useNavigate } from "react-router-dom";
import IaLoader from "./IaLoader";

export default function Profile() {
  //Loader de IA
  const navigate = useNavigate();
  const [loading, setLoading] = useState(false);

  const handleNavigateIA = () => {
    setLoading(true);
    setTimeout(() => {
      setLoading(false);
      navigate("/iaPortal");
    }, 1500);
  };
  const nav = [
    { nombre: "Menu", imagen: "/menu_icon.svg", link: "/cliente" },
    { nombre: "Historial", imagen: "/historial_icon.svg", link: "/historial" },
    { nombre: "Perfil", imagen: "/perfil_icon.svg", link: "/perfil" },
    {
      nombre: "IA",
      imagen: "/ia_icon.png",
      link: "/iaPortal",
      custom: handleNavigateIA,
    },
  ];

  const icons = [
    { nombre: "Hamburguesas", imagen: "/icon_hamburguesa.svg" },
    { nombre: "Perros", imagen: "/icon_hot_dog.png" },
    { nombre: "Pizzas", imagen: "/icon_pizza.svg" },
  ];

  const [showNav, setShowNav] = useState(false);
  const [category, setCategory] = useState();
  const [edit, setEdit] = useState(false);

  return (
    <main className={styles.contenedor}>
      <div
        onClick={() => setShowNav(!showNav)}
        className={`${showNav && styles.overlay}`}
      ></div>
      <div
        className={`${styles.nav} ${showNav ? styles.visible : styles.hidden}`}
      >
        <NavApp elementos={nav} />
      </div>
      <div className={styles.contenedorPrincipal}>
        <div className={styles.contenedorMenu}>
          <button className={styles.menu} onClick={() => setShowNav(!showNav)}>
            <img src="/menu_hamburguesa.svg" alt="" />
          </button>
          <img className={styles.logo} src="/Logo.png" alt="" />
        </div>
        <div className={styles.informacion}>
          <img className={styles.profile} src="/profile.jpg" alt="" />
          <div className={styles.contenedorTexto}>
            <h1 className={styles.texto}>Informacion personal</h1>
            <div className={`${styles.contenedorRow} ${edit && styles.active}`}>
              <div>
                <label htmlFor="nombre" className={styles.titulo}>
                  Nombre
                </label>
                <p
                  className={`${styles.contenido} ${
                    edit ? styles.ocultar : styles.mostrar
                  }`}
                >
                  Maria Camila Montilla Orozco
                </p>
                <input
                  className={`${styles.inputNumero} ${
                    edit ? styles.mostrar : styles.ocultar
                  }`}
                  type="text"
                  name="nombre"
                  id="nombre"
                />
              </div>
              <div>
                <label htmlFor="email" className={styles.titulo}>
                  Email
                </label>
                <p
                  className={`${styles.contenido} ${
                    edit ? styles.ocultar : styles.mostrar
                  }`}
                >
                  camila@correo.com
                </p>
                <input
                  className={`${styles.inputNumero} ${
                    edit ? styles.mostrar : styles.ocultar
                  }`}
                  type="email"
                  name="email"
                  id="email"
                />
              </div>
              <div>
                <label htmlFor="celular" className={styles.titulo}>
                  Celular
                </label>
                <p
                  className={`${styles.contenido} ${
                    edit ? styles.ocultar : styles.mostrar
                  }`}
                >
                  +57 315 263 547
                </p>
                <input
                  className={`${styles.inputNumero} ${
                    edit ? styles.mostrar : styles.ocultar
                  }`}
                  type="text"
                  name="celular"
                  id="celular"
                />
              </div>
              <div>
                <label htmlFor="direccion" className={styles.titulo}>
                  Dirección
                </label>
                <p
                  className={`${styles.contenido} ${
                    edit ? styles.ocultar : styles.mostrar
                  }`}
                >
                  Barrio el nogal, Armenia
                </p>
                <input
                  className={`${styles.inputNumero} ${
                    edit ? styles.mostrar : styles.ocultar
                  }`}
                  type="text"
                  name="direccion"
                  id="direccion"
                />
              </div>
            </div>
          </div>
          <button className={styles.editar} onClick={() => setEdit(!edit)}>
            <img className={styles.editarIcon} src="/editar_icon.png" alt="" />
          </button>
        </div>
        <div className={styles.gustos}>
          <div className={styles.preferencias}>
            <p className={styles.parrafo}>
              ¡Ingresa tus gustos personales, así nuestra IA podrá asistirte en
              tus pedidos!
            </p>
            <div className={styles.campo}>
              <label className={styles.tituloGrande} htmlFor="ingredientes">
                Ingredientes Favoritos
              </label>
              <textarea name="ingredientes" id="ingredientes"></textarea>
            </div>
            <div className={styles.campo}>
              <label className={styles.tituloGrande} htmlFor="restricciones">
                Restricciones en tu dieta
              </label>
              <textarea name="restricciones" id="restricciones"></textarea>
            </div>
            <div className={styles.campo}>
              <label className={styles.tituloGrande} htmlFor="Expectativas">
                Expectativas
              </label>
              <textarea name="Expectativas" id="Expectativas"></textarea>
            </div>
          </div>
          <div className={styles.categorias}>
            <p className={styles.parrafo}>Comida favoritas</p>
            <div className={styles.categoria}>
              {icons.map((categoria) => (
                <CategoryIcon
                  key={categoria.imagen}
                  categoria={categoria}
                  setCategory={setCategory}
                  selected={category === categoria.nombre}
                />
              ))}
            </div>
          </div>
        </div>
        <div className={styles.pago}>
          <div className={styles.info}>
            <p className={styles.parrafo}>Configura tus metodos de pago</p>
            <div className={styles.contenedorInput}>
              <label className={styles.tituloGrande} htmlFor="metodo">
                Método de pago
              </label>
              <input
                className={styles.inputMetodo}
                type="text"
                name="metodo"
                id="metodo"
              />
            </div>
          </div>
          <div className={styles.info}>
            <div className={styles.contenedorInput}>
              <label className={styles.tituloGrande} htmlFor="numero">
                Numero de tarjeta
              </label>
              <input
                className={styles.inputNumero}
                type="number"
                name="numero"
                id="numero"
              />
            </div>
            <div className={styles.contenedorInput}>
              <label className={styles.tituloGrande} htmlFor="nombre">
                Nombre en tarjeta
              </label>
              <input
                className={styles.inputNumero}
                type="text"
                name="nombre"
                id="nombre"
              />
            </div>
            <div className={styles.contenedorInput}>
              <label className={styles.tituloGrande} htmlFor="cvv">
                CVV
              </label>
              <input
                className={styles.inputNumero}
                type="number"
                name="cvv"
                id="cvv"
              />
            </div>
          </div>
        </div>
        <div className={styles.botones}>
          <button className={styles.boton}>GUARDAR</button>
          <Link to="/" className={styles.botonLink}>
            <button className={styles.boton}>CERRAR SESION</button>
          </Link>
        </div>
      </div>
      {loading && <IaLoader />}
    </main>
  );
}
