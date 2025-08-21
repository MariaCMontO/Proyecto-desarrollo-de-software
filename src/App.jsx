import { BrowserRouter } from "react-router-dom";
import AppRoutes from "./routes/AppRoutes";
import Landing from "./pages/landing/Landing";

function App() {
  return (
      <BrowserRouter>
        <AppRoutes />
      </BrowserRouter>
  );
}

export default App;
