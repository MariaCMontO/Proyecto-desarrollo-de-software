import { createContext, ReactNode, useContext, useReducer } from "react";
import { carritoReducer, stateInicial } from "../reducer/cart-reducer";

//Creamos el context
const CarritoContext = createContext(null);

//Creamos el provider que envuelve el context
export function CarritoProvider({children}){
    const [state, dispatch]= useReducer(carritoReducer, stateInicial);

    return (
        <CarritoContext.Provider value={{state, dispatch}}>
            {children}
        </CarritoContext.Provider>
    )
} 

//Hook para usar el contexto
export function useCarritoContext(){
    const context=useContext(CarritoContext)

    if(!context){
        throw new Error('useCarrito debe usarse dentro de CarritoProvider')
    }

    return context;
}