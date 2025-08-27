import { Route, Routes } from "react-router-dom";
import Landing from "../pages/landing/Landing";
import Login from "../pages/login/Login";
import Registration from "../pages/login/Registration";
import AppClients from "../pages/client/AppClient";
import AppAdmin from "../pages/admin/AppAdmin";
import Profile from "../pages/client/Profile";
import Historial from "../pages/client/Historial";
import Payment from "../pages/client/Payment";

export default function AppRoutes() {
  return (
    <Routes>
      {/* Landing page -- Es la raiz, se renderiza cuando se ejecuta la pagina*/}
      <Route path="/" element={<Landing />} />

      {/* Login*/}
      <Route path="/login" element={<Login />} />

      {/* Registration*/}
      <Route path="/registro" element={<Registration />} />

      {/* App Clients*/}
      <Route path="/cliente" element={<AppClients />} />

      {/* Clients profile*/}
      <Route path="/perfil" element={<Profile />} />

      {/* Clients record*/}
      <Route path="/historial" element={<Historial />} />

      {/* Clients payment receipt*/}
      <Route path="/pago" element={<Payment />} />

      {/* App Admin*/}
      <Route path="/admin" element={<AppAdmin />} />

      {/* 404 */}
      <Route path="*" element={<h1>404 - Página no encontrada</h1>} />
    </Routes>
  );
}

