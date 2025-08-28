//Creamos el estado inicial con local storage
const historialInicial = () => {
  const localStorageHistorial = localStorage.getItem("historial");
  return localStorageHistorial ? JSON.parse(localStorageHistorial) : [];
};

//State inicial
export const stateInicial = {
  historial: historialInicial(),
};

//Creamos el reducer
export const historialReducer = (state = stateInicial, action) => {
  if (action.type === "Agregar al historial") {
    console.log("entro a añadir al historial");
    const nuevaOrden = {
      id:crypto.randomUUID().substring(0,5),
      productos: action.payload.productos,
      cliente: action.payload.cliente,
      estado: "Confirmada"
    };
    const nuevoHistorial = [...state.historial, nuevaOrden];
    return {
      ...state,
      historial: nuevoHistorial,
    };
  }

  return state;
};
