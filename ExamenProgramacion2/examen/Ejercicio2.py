class Persona:
    def __init__(self, nombre, edad, peso):
        self.nombre = nombre
        self.edad = edad
        self.pesoPersona = peso
class Cabina:
    def __init__(self, nroCabina):
        self.nroCabina = nroCabina
        self.personasAbordo = []
    def agregarPersona(self, p):
        if len(self.personasAbordo) >= 10:
            return False
        peso_actual = 0
        for per in self.personasAbordo:
            peso_actual += per.pesoPersona
        if peso_actual + p.pesoPersona > 850:
            return False
        self.personasAbordo.append(p)
        return True
class Linea:
    def __init__(self, color):
        self.color = color
        self.filaPersonas = []
        self.cabinas = []
        self.cantidadCabinas = 0
    def agregarPersona(self, p):
        self.filaPersonas.append(p)
    def agregarCabina(self, nroCab):
        cab = Cabina(nroCab)
        self.cabinas.append(cab)
        self.cantidadCabinas += 1
    def agregarPrimeraPersonaACabina(self, nroCab):
        for cab in self.cabinas:
            if cab.nroCabina == nroCab:
                if len(self.filaPersonas) > 0:
                    persona = self.filaPersonas.pop(0)
                    return cab.agregarPersona(persona)
        return False
    def verificarReglas(self):
        for cab in self.cabinas:
            peso_total = 0
            for p in cab.personasAbordo:
                peso_total += p.pesoPersona

            if len(cab.personasAbordo) > 10 or peso_total > 850:
                return False
        return True
    def calcularIngreso(self):
        total = 0
        for cab in self.cabinas:
            for p in cab.personasAbordo:
                if p.edad < 25 or p.edad > 60:
                    total += 1.5
                else:
                    total += 3
        return total
    def ingresoRegular(self):
        total = 0
        for cab in self.cabinas:
            for p in cab.personasAbordo:
                if 25 <= p.edad <= 60:
                    total += 3
        return total
class MiTeleferico:
    def __init__(self):
        self.lineas = [
            Linea("Amarillo"),
            Linea("Rojo"),
            Linea("Verde")
        ]
        self.cantidadIngresos = 0
    def buscarLinea(self, nombre):
        for ln in self.lineas:
            if ln.color == nombre:
                return ln
        return None
    def agregarPersonaFila(self, persona, nombreLinea):
        linea = self.buscarLinea(nombreLinea)
        if linea:
            linea.agregarPersona(persona)
    def agregarCabina(self, nombreLinea, nroCab):
        linea = self.buscarLinea(nombreLinea)
        if linea:
            linea.agregarCabina(nroCab)
    def verificarTodas(self):
        for ln in self.lineas:
            if not ln.verificarReglas():
                return False
        return True
    def calcularIngresoTotal(self):
        total = 0
        for ln in self.lineas:
            total += ln.calcularIngreso()
        self.cantidadIngresos = total
        return total
    def lineaMasIngresoRegular(self):
        mayor = None
        mayor_ing = 0
        for ln in self.lineas:
            ing = ln.ingresoRegular()
            if ing > mayor_ing:
                mayor_ing = ing
                mayor = ln
        if mayor:
            return mayor.color
        return None
sistema = MiTeleferico()
sistema.agregarCabina("Amarillo", 1)
sistema.agregarCabina("Amarillo", 2)
sistema.agregarCabina("Rojo", 1)
sistema.agregarPersonaFila(Persona("Ana", 20, 55), "Amarillo")
sistema.agregarPersonaFila(Persona("Luis", 30, 70), "Amarillo")
sistema.agregarPersonaFila(Persona("Carlos", 65, 80), "Rojo")
sistema.agregarPersonaFila(Persona("Maria", 22, 60), "Rojo")
lineaA = sistema.buscarLinea("Amarillo")
lineaA.agregarPrimeraPersonaACabina(1)
lineaA.agregarPrimeraPersonaACabina(2)
lineaR = sistema.buscarLinea("Rojo")
lineaR.agregarPrimeraPersonaACabina(1)
lineaR.agregarPrimeraPersonaACabina(1)
print("¿Todas las cabinas cumplen las reglas?:", sistema.verificarTodas())
print("Ingreso total:", sistema.calcularIngresoTotal(), "Bs")
print("Línea con mayor ingreso regular:", sistema.lineaMasIngresoRegular())