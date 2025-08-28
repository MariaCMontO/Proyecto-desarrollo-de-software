import { createContext, useContext, useReducer, useState } from "react";
import { historialReducer, stateInicial } from "../reducer/historial-reducer";


//Creamos el context
const HistorialContext= createContext(null);

//Creamos el provider

export function HistorialProvider({children}){
    const [state, dispatch]= useReducer(historialReducer, stateInicial)

    return (
        <HistorialContext.Provider value={{state, dispatch}}>
            {children}
        </HistorialContext.Provider>
    )
}

//Hook para usar el context
export function useHistorialContext(){
    const context= useContext(HistorialContext)

    if(!context){
        throw new Error('useCarrito debe usarse dentro de HistorialProvider')
    }

    return context;
}