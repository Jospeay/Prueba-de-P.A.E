package jpa.ni.practicapae.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import jpa.ni.practicapae.Model.Participante;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParticipanteController {

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtEdad;
    @FXML
    private TextField txtTelefono;
    @FXML
    private ComboBox<String> cmbCategoria;
    @FXML
    private RadioButton rbMasculino;
    @FXML
    private RadioButton rbFemenino;
    @FXML
    private CheckBox chkIndividual;
    @FXML
    private CheckBox chkParejas;
    @FXML
    private CheckBox chkEquipos;
    @FXML
    private CheckBox chkFederado;
    @FXML
    private CheckBox chkExperiencia;
    @FXML
    private CheckBox chkDisponibilidad;
    @FXML
    private CheckBox chkSeguro;
    @FXML
    private CheckBox chkFutbol;
    @FXML
    private CheckBox chkBaloncesto;
    @FXML
    private CheckBox chkVoleibol;
    @FXML
    private CheckBox chkAtletismo;
    @FXML
    private CheckBox chkNatacion;
    @FXML
    private CheckBox chkTenis;
    @FXML
    private TableView<Participante> tablaParticipantes;
    @FXML
    private TableColumn<Participante, String> colNombre;
    @FXML
    private TableColumn<Participante, Integer> colEdad;
    @FXML
    private TableColumn<Participante, String> colCategoria;
    @FXML
    private TableColumn<Participante, String> colGenero;
    @FXML
    private TableColumn<Participante, String> colModalidad;
    @FXML
    private TableColumn<Participante, String> colDisciplina;
    @FXML
    private TableColumn<Participante, String> colEstado;
    @FXML
    private Button btnRegistrar;

    private ObservableList<Participante> listaParticipantes =
            FXCollections.observableArrayList();

    // Guarda el participante que se está editando actualmente (null = modo "registrar nuevo")
    private Participante participanteEnEdicion = null;

    @FXML
    public void initialize() {
        cmbCategoria.getItems().addAll(
                "Juvenil",
                "Intermedia",
                "Senior"
        );

        ToggleGroup grupoGenero = new ToggleGroup();
        rbMasculino.setToggleGroup(grupoGenero);
        rbFemenino.setToggleGroup(grupoGenero);

        colNombre.setCellValueFactory(
                new javafx.scene.control.cell.PropertyValueFactory<>("nombre")
        );
        colEdad.setCellValueFactory(
                new javafx.scene.control.cell.PropertyValueFactory<>("edad")
        );
        colCategoria.setCellValueFactory(
                new javafx.scene.control.cell.PropertyValueFactory<>("categoria")
        );
        colGenero.setCellValueFactory(
                new javafx.scene.control.cell.PropertyValueFactory<>("genero")
        );
        colModalidad.setCellValueFactory(
                new javafx.scene.control.cell.PropertyValueFactory<>("modalidad")
        );
        colDisciplina.setCellValueFactory(
                new javafx.scene.control.cell.PropertyValueFactory<>("disciplina")
        );
        colEstado.setCellValueFactory(
                new javafx.scene.control.cell.PropertyValueFactory<>("estado")
        );
        tablaParticipantes.setItems(listaParticipantes);
    }

    @FXML
    public void registrarParticipante(ActionEvent event) {

        String nombre = txtNombre.getText().trim();
        String edadTexto = txtEdad.getText().trim();
        String telefono = txtTelefono.getText().trim();

        if (nombre.isEmpty()) {
            mostrarAlerta("Error", "Debe ingresar el nombre completo");
            return;
        }

        if (nombre.length() < 5) {
            mostrarAlerta("Error", "El nombre debe tener al menos 5 caracteres");
            return;
        }

        int edad;
        try {
            edad = Integer.parseInt(edadTexto);
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "La edad debe ser un número válido");
            return;
        }

        if (edad < 15 || edad > 60) {
            mostrarAlerta("Error", "La edad debe estar entre 15 y 60 años");
            return;
        }

        if (!telefono.matches("\\d+")) {
            mostrarAlerta("Error", "El teléfono solo debe contener números");
            return;
        }

        if (cmbCategoria.getValue() == null) {
            mostrarAlerta("Error", "Debe seleccionar una categoría");
            return;
        }

        String genero;
        if (rbMasculino.isSelected()) {
            genero = "Masculino";
        } else if (rbFemenino.isSelected()) {
            genero = "Femenino";
        } else {
            mostrarAlerta("Error", "Debe seleccionar un género");
            return;
        }

        List<String> modalidades = new ArrayList<>();
        if (chkIndividual.isSelected()) modalidades.add("Individual");
        if (chkParejas.isSelected()) modalidades.add("Parejas");
        if (chkEquipos.isSelected()) modalidades.add("Equipos");

        if (modalidades.isEmpty()) {
            mostrarAlerta("Error", "Debe seleccionar al menos una modalidad");
            return;
        }
        String modalidadFinal = String.join(", ", modalidades);

        List<String> caracteristicas = new ArrayList<>();
        if (chkFederado.isSelected()) caracteristicas.add("Federado");
        if (chkExperiencia.isSelected()) caracteristicas.add("Experiencia previa");
        if (chkDisponibilidad.isSelected()) caracteristicas.add("Disponible fines de semana");
        if (chkSeguro.isSelected()) caracteristicas.add("Seguro deportivo");

        String caracteristicasFinal = caracteristicas.isEmpty()
                ? "Ninguna"
                : String.join(", ", caracteristicas);

        List<String> disciplinas = new ArrayList<>();
        if (chkFutbol.isSelected()) disciplinas.add("Fútbol");
        if (chkBaloncesto.isSelected()) disciplinas.add("Baloncesto");
        if (chkVoleibol.isSelected()) disciplinas.add("Voleibol");
        if (chkAtletismo.isSelected()) disciplinas.add("Atletismo");
        if (chkNatacion.isSelected()) disciplinas.add("Natación");
        if (chkTenis.isSelected()) disciplinas.add("Tenis");

        if (disciplinas.isEmpty()) {
            mostrarAlerta("Error", "Debe seleccionar al menos una disciplina");
            return;
        }
        String disciplinasFinal = String.join(", ", disciplinas);

        if (participanteEnEdicion == null) {
            // ---- MODO REGISTRAR: crear un participante nuevo ----
            Participante participante = new Participante(
                    nombre, edad, telefono, cmbCategoria.getValue(), genero,
                    modalidadFinal, caracteristicasFinal, disciplinasFinal, "Inscrito");

            listaParticipantes.add(participante);
            mostrarInformacion("Registro exitoso", "El participante fue registrado correctamente");
        } else {
            // ---- MODO ACTUALIZAR: sobreescribir los datos del seleccionado ----
            participanteEnEdicion.setNombre(nombre);
            participanteEnEdicion.setEdad(edad);
            participanteEnEdicion.setTelefono(telefono);
            participanteEnEdicion.setCategoria(cmbCategoria.getValue());
            participanteEnEdicion.setGenero(genero);
            participanteEnEdicion.setModalidad(modalidadFinal);
            participanteEnEdicion.setCaracteristicas(caracteristicasFinal);
            participanteEnEdicion.setDisciplina(disciplinasFinal);
            // el estado se conserva tal cual estaba

            tablaParticipantes.refresh(); // repinta la tabla con los datos nuevos
            mostrarInformacion("Actualización exitosa", "El participante fue actualizado correctamente");

            participanteEnEdicion = null;
            btnRegistrar.setText("Registrar");
        }

        limpiarCampos(null);
    }

    /**
     * Botón "Actualizar": toma el participante seleccionado en la tabla
     * y vuelca sus datos en el formulario para que el usuario los edite.
     * El guardado real ocurre al presionar "Registrar" (que en este momento
     * actúa como "Guardar cambios").
     */
    @FXML
    public void cargarParticipanteParaEditar(ActionEvent event) {

        Participante seleccionado = tablaParticipantes.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            mostrarAlerta("Error", "Debe seleccionar un participante de la tabla para actualizar");
            return;
        }

        participanteEnEdicion = seleccionado;

        txtNombre.setText(seleccionado.getNombre());
        txtEdad.setText(String.valueOf(seleccionado.getEdad()));
        txtTelefono.setText(seleccionado.getTelefono());
        cmbCategoria.setValue(seleccionado.getCategoria());

        rbMasculino.setSelected("Masculino".equals(seleccionado.getGenero()));
        rbFemenino.setSelected("Femenino".equals(seleccionado.getGenero()));

        List<String> modalidades = Arrays.asList(seleccionado.getModalidad().split(",\\s*"));
        chkIndividual.setSelected(modalidades.contains("Individual"));
        chkParejas.setSelected(modalidades.contains("Parejas"));
        chkEquipos.setSelected(modalidades.contains("Equipos"));

        List<String> caracteristicas = Arrays.asList(seleccionado.getCaracteristicas().split(",\\s*"));
        chkFederado.setSelected(caracteristicas.contains("Federado"));
        chkExperiencia.setSelected(caracteristicas.contains("Experiencia previa"));
        chkDisponibilidad.setSelected(caracteristicas.contains("Disponible fines de semana"));
        chkSeguro.setSelected(caracteristicas.contains("Seguro deportivo"));

        List<String> disciplinas = Arrays.asList(seleccionado.getDisciplina().split(",\\s*"));
        chkFutbol.setSelected(disciplinas.contains("Fútbol"));
        chkBaloncesto.setSelected(disciplinas.contains("Baloncesto"));
        chkVoleibol.setSelected(disciplinas.contains("Voleibol"));
        chkAtletismo.setSelected(disciplinas.contains("Atletismo"));
        chkNatacion.setSelected(disciplinas.contains("Natación"));
        chkTenis.setSelected(disciplinas.contains("Tenis"));

        // Cambiamos el texto del botón para que sea claro que ahora se va a guardar una edición
        btnRegistrar.setText("Guardar cambios");
    }

    @FXML
    public void limpiarCampos(ActionEvent event) {

        txtNombre.clear();
        txtEdad.clear();
        txtTelefono.clear();
        cmbCategoria.setValue(null);
        rbMasculino.setSelected(false);
        rbFemenino.setSelected(false);
        chkIndividual.setSelected(false);
        chkParejas.setSelected(false);
        chkEquipos.setSelected(false);
        chkFederado.setSelected(false);
        chkExperiencia.setSelected(false);
        chkDisponibilidad.setSelected(false);
        chkSeguro.setSelected(false);
        chkFutbol.setSelected(false);
        chkBaloncesto.setSelected(false);
        chkVoleibol.setSelected(false);
        chkAtletismo.setSelected(false);
        chkNatacion.setSelected(false);
        chkTenis.setSelected(false);

        // Si se cancela sin guardar, también salimos del modo edición
        participanteEnEdicion = null;
        btnRegistrar.setText("Registrar");
    }

    @FXML
    public void eliminarParticipante(ActionEvent event) {

        Participante seleccionado = tablaParticipantes.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            mostrarAlerta("Error", "Debe seleccionar un participante de la tabla");
            return;
        }

        listaParticipantes.remove(seleccionado);
        mostrarInformacion("Eliminado", "Participante eliminado correctamente");
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    private void mostrarInformacion(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}