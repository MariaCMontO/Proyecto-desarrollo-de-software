import { useEffect, useState } from "react";
import NavApp from "../../components/NavApp";
import styles from "./Historial.module.css";
import { useHistorialContext } from "../../context/historialContext";
import HistorialDetail from "../../components/HistorialDetail";

export default function Historial() {
  const nav = [
    { nombre: "Menu", imagen: "/menu_icon.svg", link: "/cliente" },
    { nombre: "Historial", imagen: "/historial_icon.svg", link: "/historial" },
    { nombre: "Perfil", imagen: "/perfil_icon.svg", link: "/perfil" },
    {
      nombre: "IA",
      imagen: "/ia_icon.png",
      link: "/profile",
      link: "/cliente",
    },
  ];

  const [showNav, setShowNav] = useState(false);
  const { state, dispatch } = useHistorialContext();

  useEffect(() => {
    localStorage.setItem("historial", JSON.stringify(state.historial));
  }, [state.historial]);

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
        <div className={styles.contenedorHistorial}>
          {state.historial
          .filter((orden) => orden.cliente==='Juan' && orden.estado==='Lista')
          .map((orden) => (
            <HistorialDetail
            key={orden.id}
            orden={orden}
            />
          ))}
        </div>
      </div>
    </main>
  );
}
