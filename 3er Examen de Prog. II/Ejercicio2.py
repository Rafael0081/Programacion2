import json
import os
BASE_DIR = os.path.dirname(os.path.abspath(__file__))
CARPETA = os.path.join(BASE_DIR, "datos")
if not os.path.exists(CARPETA):
    os.makedirs(CARPETA)
class Medico:
    def __init__(self, idMed, nombreMed, apellidoMed, anosExperiencia):
        self.idMed = idMed
        self.nombreMed = nombreMed
        self.apellidoMed = apellidoMed
        self.anosExperiencia = anosExperiencia

    def to_dict(self):
        return {
            "idMed": self.idMed,
            "nombreMed": self.nombreMed,
            "apellidoMed": self.apellidoMed,
            "anosExperiencia": self.anosExperiencia
        }


class Consulta:
    def __init__(self, ci, nombrePaciente, apellidoPaciente, idMed, dia, mes, anio):
        self.ci = ci
        self.nombrePaciente = nombrePaciente
        self.apellidoPaciente = apellidoPaciente
        self.idMed = idMed
        self.dia = dia
        self.mes = mes
        self.anio = anio

    def to_dict(self):
        return {
            "ci": self.ci,
            "nombrePaciente": self.nombrePaciente,
            "apellidoPaciente": self.apellidoPaciente,
            "idMed": self.idMed,
            "dia": self.dia,
            "mes": self.mes,
            "anio": self.anio
        }


class Consultorio:
    def __init__(self):
        self.medicos = []
        self.consultas = []
        self.cargar_archivos()

    
    def cargar_archivos(self):
        ruta_medicos = os.path.join(CARPETA, "medicos.json")
        ruta_consultas = os.path.join(CARPETA, "consultas.json")

        if os.path.exists(ruta_medicos):
            with open(ruta_medicos, "r") as f:
                data = json.load(f)
                for m in data:
                    self.medicos.append(Medico(
                        m["idMed"], m["nombreMed"], m["apellidoMed"], m["anosExperiencia"]
                    ))

        if os.path.exists(ruta_consultas):
            with open(ruta_consultas, "r") as f:
                data = json.load(f)
                for c in data:
                    self.consultas.append(Consulta(
                        c["ci"], c["nombrePaciente"], c["apellidoPaciente"],
                        c["idMed"], c["dia"], c["mes"], c["anio"]
                    ))

    
    def guardar_medicos(self):
        ruta = os.path.join(CARPETA, "medicos.json")
        with open(ruta, "w") as f:
            json.dump([m.to_dict() for m in self.medicos], f, indent=4)

    def guardar_consultas(self):
        ruta = os.path.join(CARPETA, "consultas.json")
        with open(ruta, "w") as f:
            json.dump([c.to_dict() for c in self.consultas], f, indent=4)

   
    def altaMedico(self, medico):
        self.medicos.append(medico)
        self.guardar_medicos()

    def altaConsulta(self, consulta):
        self.consultas.append(consulta)
        self.guardar_consultas()

    
    def bajaMedico(self, nombreX, apellidoY):
        med_borrar = None
        for m in self.medicos:
            if m.nombreMed == nombreX and m.apellidoMed == apellidoY:
                med_borrar = m
                break

        if med_borrar is None:
            print("El médico no existe.")
            return

        
        self.medicos.remove(med_borrar)
        self.guardar_medicos()

        
        nuevas = []
        for c in self.consultas:
            if c.idMed != med_borrar.idMed:
                nuevas.append(c)

        self.consultas = nuevas
        self.guardar_consultas()

        print(f"Médico {nombreX} {apellidoY} y sus consultas eliminados.")

    
    def cambiarConsultasFestivas(self):
        for c in self.consultas:
            if (c.dia == 25 and c.mes == "diciembre") or (c.dia == 1 and c.mes == "enero"):
                c.dia = 2  # mover la fecha

        self.guardar_consultas()
        print("Consultas de navidad / año nuevo actualizadas.")

    
    def pacientesCumple(self, dia, mes):
        print(f"Pacientes atendidos el {dia} de {mes}:")
        for c in self.consultas:
            if c.dia == dia and c.mes == mes:
                print(f"• {c.nombrePaciente} {c.apellidoPaciente} (CI: {c.ci})")



consultorio = Consultorio()

consultorio.altaMedico(Medico(1, "Juan", "Flores", 10))
consultorio.altaMedico(Medico(2, "Maria", "Torrez", 5))
consultorio.altaMedico(Medico(3, "Luis", "Ramos", 8))

consultorio.altaConsulta(Consulta(123, "Ana", "Lopez", 1, 25, "diciembre", 2024))
consultorio.altaConsulta(Consulta(124, "Jose", "Perez", 1, 1, "enero", 2024))
consultorio.altaConsulta(Consulta(125, "Miguel", "Flores", 1, 10, "marzo", 2024))

consultorio.altaConsulta(Consulta(126, "Laura", "Mamani", 2, 10, "mayo", 2024))
consultorio.altaConsulta(Consulta(127, "Rosa", "Gomez", 2, 15, "junio", 2024))
consultorio.altaConsulta(Consulta(128, "Carlos", "Vargas", 2, 25, "diciembre", 2024))

consultorio.altaConsulta(Consulta(129, "Kevin", "Choque", 3, 20, "abril", 2024))
consultorio.altaConsulta(Consulta(130, "Lucia", "Cruz", 3, 1, "enero", 2024))
consultorio.altaConsulta(Consulta(131, "Julio", "Quispe", 3, 8, "septiembre", 2024))


consultorio.bajaMedico("Maria", "Torrez")

consultorio.cambiarConsultasFestivas()


consultorio.pacientesCumple(10, "marzo")
