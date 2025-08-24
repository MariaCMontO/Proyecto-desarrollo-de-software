import CategoryIcon from "../../components/CategoryIcon";
import NavApp from "../../components/NavApp";
import styles from "./AppClient.module.css";
import data from "../../data/productData.json";
import ProductDetail from "./ProductDetail";
import CartDetail from "../../components/CartDetail";
import CartSumary from "../../components/CartSumary";
import { useCarritoContext } from "../../context/carritoContext";

export default function AppClient() {
  const nav = [
    { nombre: "Menu", imagen: "/menu_icon.svg" },
    { nombre: "Historial", imagen: "/historial_icon.svg" },
    { nombre: "Perfil", imagen: "/perfil_icon.svg" },
    { nombre: "IA", imagen: "/ia_icon.png" },
  ];

  const icons = [
    { nombre: "Hamburguesas", imagen: "/icon_hamburguesa.svg" },
    { nombre: "Perros", imagen: "/icon_hot_dog.png" },
    { nombre: "Pizzas", imagen: "/icon_pizza.svg" },
  ];

  const { state, dispatch } = useCarritoContext();

  return (
    <main className={styles.contenedor}>
      <div className={styles.nav}>
        <NavApp elementos={nav} />
      </div>
      <div className={styles.contenedorPrincipal}>
        <div className={styles.contenedorSecundario}>
          <div className={styles.contenedorNav}>
            <button className={styles.menu}>
              <img src="/menu_hamburguesa.svg" alt="" />
            </button>
            <div className={styles.categoria}>
              {icons.map((categoria) => (
                <CategoryIcon key={categoria.imagen} categoria={categoria} />
              ))}
            </div>
            <button className={styles.botonCarrito}>
              <img src="/cart_icon.svg" alt="" />
            </button>
          </div>
          <div className={styles.productContainer}>
            {data.map((product) => (
              <ProductDetail key={product.id} producto={product} />
            ))}
          </div>
        </div>
        <div className={styles.contenedorCarrito}>
          <img className={styles.logo} src="/Logo.png" alt="" />
          <div className={styles.contenedorFlex}>
            <p className={styles.texto}>ORDEN</p>
            <button onClick={() => dispatch({type:"Vaciar carrito"})} className={styles.vaciarBoton}>Vaciar carrito</button>
          </div>
          <div className={styles.carrito}>
            {state.carrito.map((producto) => (
              <CartDetail key={producto.id} producto={producto} />
            ))}
          </div>
          <div className={styles.resumen}>
            <CartSumary />
          </div>
        </div>
      </div>
    </main>
  );
}
