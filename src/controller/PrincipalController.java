package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
//import javafx.scene.control.cell.PropertyValueFactory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Callback;


import javafx.stage.StageStyle;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import model.Tarea;
import dao.RegistrarTarea;

public class PrincipalController implements  Initializable{

	@FXML
    private Button btnCancelar;

    @FXML
    private Button btnGuardar;

    @FXML
    private CheckBox checkCorreo;

    @FXML
    private CheckBox checkFisico;

    @FXML
    private CheckBox checkPortal;

    @FXML
    private ComboBox<String> comboEstado; //private ComboBox<tipo Objeto> comboEstado;

    @FXML
    private DatePicker fechaCulminacion;

    @FXML
    private RadioButton rbAlta;

    @FXML
    private RadioButton rbBaja;

    @FXML
    private RadioButton rbMedia;

    @FXML
    private TextArea txtAreaDescripcion;

    @FXML
    private TextField txtTitulo;
    
    @FXML
    private TableView<Tarea> tableTareas = new TableView<Tarea>(); 
    
    private RegistrarTarea tareaDao;
    
    private ContextMenu cmOpciones;
    
    private Tarea tareaSelect; // objeto o tarea seleccinada
    
	public void initialize(URL url, ResourceBundle rb ) {
		//COMBO BOX
		String[] opciones = {"Pendiente", "En proceso", "Cancelado","Terminado"};
		ObservableList<String> items = FXCollections.observableArrayList(opciones);
		comboEstado.setItems(items);
		comboEstado.setValue("Seleccione");
		//RADIO BUTTON SOLO SEPUEDE SELECCIONAR UNO SOLO
		
		ToggleGroup group = new ToggleGroup();
		rbBaja.setToggleGroup(group);;
		rbMedia.setToggleGroup(group);
		rbAlta.setToggleGroup(group);
		
		this.tareaDao = new RegistrarTarea();
		cargarTareas();
		// click derecho EDDITAR ------------------------------------
		cmOpciones = new ContextMenu();
		
		MenuItem miEditar = new MenuItem("Editar");
		
		cmOpciones.getItems().addAll(miEditar);
		
		miEditar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				
				int index = tableTareas.getSelectionModel().getSelectedIndex();// capturamos la fila seleccionada
				
				tareaSelect = tableTareas.getItems().get(index);
				
				System.out.println(tareaSelect);
				
				txtTitulo.setText(tareaSelect.getTitulo());
				txtAreaDescripcion.setText(tareaSelect.getDescripcion());
				
				switch( tareaSelect.getPrioridad() ) {
				case "Baja": rbBaja.setSelected(true); break;
				case "Media": rbMedia.setSelected(true); break;
				case "Alta": rbAlta.setSelected(true); break;
				}
				
				DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				fechaCulminacion.setValue(LocalDate.parse(tareaSelect.getFechaCulminacion(), formater));
				
				checkCorreo.setSelected(tareaSelect.getCorreo().equals("Si") ? true : false );
				checkPortal.setSelected(tareaSelect.getPortalWeb().equals("Si") ? true : false );
				checkFisico.setSelected(tareaSelect.getFisico().equals("Si") ? true : false );
				
				comboEstado.getSelectionModel().select(tareaSelect.getEstado());
				
				btnCancelar.setDisable(false);
			}
		});
		
		tableTareas.setContextMenu(cmOpciones);
		
		
	}
	@FXML
	public void guardarDatos(ActionEvent event) {
		
		if(tareaSelect == null)//  cuando no se ha seleccionado nunguna fila
		{
			Tarea tarea = new Tarea();
			tarea.setTitulo(txtTitulo.getText());
			tarea.setDescripcion(txtAreaDescripcion.getText());
			
			if( rbBaja.isSelected()) {
				tarea.setPrioridad("Baja");
			} else if(rbMedia.isSelected()) {
				tarea.setPrioridad("Media");
			} else {
				tarea.setPrioridad("Alta");
			}
			
			tarea.setFechaCulminacion(fechaCulminacion.getValue().toString());
			tarea.setCorreo(checkCorreo.isSelected() == true ? "Si" : "No");
			tarea.setPortalWeb(checkPortal.isSelected() == true ? "Si" : "No");
			tarea.setFisico(checkFisico.isSelected() == true ? "Si" : "No");
			
			tarea.setEstado(comboEstado.getSelectionModel().getSelectedItem());
			
			System.out.println("string tarea " + tarea.toString());
			
			boolean rsp = this.tareaDao.registrar(tarea);
			
			if(rsp) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				
				alert.setTitle("Exito");
				alert.setContentText("se registró correctamente");
				alert.initStyle(StageStyle.UTILITY);
				alert.showAndWait();
				
				limpiarCampos();
				cargarTareas();
				
			} else {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Error");
				alert.setContentText("ocurrio un error");
				alert.initStyle(StageStyle.UTILITY);
				alert.showAndWait();
			}
		
		}
		else {
			
			tareaSelect.setTitulo(txtTitulo.getText());
			tareaSelect.setDescripcion(txtAreaDescripcion.getText());
			if( rbBaja.isSelected()) {
				tareaSelect.setPrioridad("Baja");
			} else if(rbMedia.isSelected()) {
				tareaSelect.setPrioridad("Media");
			} else {
				tareaSelect.setPrioridad("Alta");
			}
			tareaSelect.setFechaCulminacion(fechaCulminacion.getValue().toString());
			tareaSelect.setCorreo(checkCorreo.isSelected() == true ? "Si" : "No");
			tareaSelect.setPortalWeb(checkPortal.isSelected() == true ? "Si" : "No");
			tareaSelect.setFisico(checkFisico.isSelected() == true ? "Si" : "No");
			tareaSelect.setEstado(comboEstado.getSelectionModel().getSelectedItem());
			
			boolean rsp = this.tareaDao.editar(tareaSelect);
			if(rsp) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				
				alert.setTitle("Exito");
				alert.setContentText("se actualizó correctamente");
				alert.initStyle(StageStyle.UTILITY);
				alert.showAndWait();
				
				limpiarCampos();
				cargarTareas();
				tareaSelect = null;	// IMPORTANTE PARA QUE SE LIMPIEN LOS DATOS
				btnCancelar.setDisable(true);
				
			} else {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Error");
				alert.setContentText("ocurrio un error al actualizar");
				alert.initStyle(StageStyle.UTILITY);
				alert.showAndWait();
			}
			
		}
		
			
		
	}
	
	private void limpiarCampos() {
		txtTitulo.setText("");
		txtAreaDescripcion.setText("");
		rbBaja.setSelected(true);
		rbMedia.setSelected(false);
		rbAlta.setSelected(false);
		fechaCulminacion.setValue(null);
		checkCorreo.setSelected(false);
		checkPortal.setSelected(false);
		checkFisico.setSelected(false);
		comboEstado.getSelectionModel().select("Seleccione");
	}
	public void cargarTareas() {
		tableTareas.getItems().clear();
		tableTareas.getColumns().clear();
		
		List<Tarea> tareas = this.tareaDao.listar();
		
		ObservableList<Tarea> data = FXCollections.observableArrayList(tareas);
		
		TableColumn idCol = new TableColumn("Id");
		idCol.setCellValueFactory(new PropertyValueFactory("id"));
		
		TableColumn tituloCol = new TableColumn("Titulo");
		tituloCol.setCellValueFactory( new PropertyValueFactory("titulo") );
		
		TableColumn descripcionCol = new TableColumn("Descripcion");
		descripcionCol.setCellValueFactory( new PropertyValueFactory("descripcion") );
		
		TableColumn prioridadCol = new TableColumn("Prioridad");
		prioridadCol.setCellValueFactory( new PropertyValueFactory("prioridad") );
		
		TableColumn fechaCol = new TableColumn("Fecha Culminacon");
		fechaCol.setCellValueFactory( new PropertyValueFactory("fechaCulminacion") );
		
		TableColumn correoCol = new TableColumn("Correo");
		correoCol.setCellValueFactory( new PropertyValueFactory("correo") );
		
		TableColumn portalCol = new TableColumn("Portal Web");
		portalCol.setCellValueFactory( new PropertyValueFactory("portalWeb") );
		
		TableColumn fisicoCol = new TableColumn("Fisico");
		fisicoCol.setCellValueFactory( new PropertyValueFactory("fisico") );
		
		TableColumn estadoCol = new TableColumn("Estado");
		estadoCol.setCellValueFactory( new PropertyValueFactory("estado") );
		//TableColumn<S, T> os
		
		
		//, tituloCol,descripcionCol, prioridadCol, fechaCol, correoCol, portalCol,fisicoCol, estadoCol
		tableTareas.setItems(data);
		tableTareas.getColumns().addAll(idCol, tituloCol,descripcionCol, prioridadCol, fechaCol, correoCol, portalCol,fisicoCol, estadoCol);
		
	}
	
	@FXML
	public void btnCancelarOnAction(ActionEvent event) {
		tareaSelect = null;
		limpiarCampos();
		btnCancelar.setDisable(true);
	}
	
	
	
}
