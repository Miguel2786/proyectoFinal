package proyectoFinal.paginator;

import java.util.List;
import java.util.ArrayList;

import org.springframework.data.domain.Page;


public class PageRender <T>{

	//Datos basicos url y Page que recibe
	private String url;
	private Page<T> page;
	
	//Datos derivados:paginas, elementos por página y pagina actual
	private int totalPaginas;
	private int numElementosPorPagina;
	private int paginaActual;
	
	//Lista de todas las paginas
	List<PageItem> paginas;

	//Constructor
	public PageRender(String url, Page<T> page) {
		
		this.url = url;
		this.page = page;
		this.paginas = new ArrayList<PageItem>();
		
		numElementosPorPagina = 6;
		totalPaginas = page.getTotalPages();
		paginaActual = page.getNumber()+1;

		int desde,hasta;
		if(totalPaginas <= numElementosPorPagina) {
			desde = 1;
			hasta = totalPaginas;
			
		}else {
			if(paginaActual <= numElementosPorPagina / 2) {
				desde = 1;
				hasta = numElementosPorPagina;
			}else if(paginaActual>=totalPaginas-numElementosPorPagina/2) {
				desde = totalPaginas - numElementosPorPagina + 1;
				hasta = numElementosPorPagina;
			}else {
				desde = totalPaginas - numElementosPorPagina/2;
				hasta = desde + numElementosPorPagina;
			}
		}
		for(int i = 0;i<hasta; i++) {
			paginas.add(new PageItem(desde+i,paginaActual==desde+i));
		}
	}
	
	
	//GETTERS

		public String getUrl() {
			return url;
		}

		public int getTotalPaginas() {
			return totalPaginas;
		}

		public int getPaginaActual() {
			return paginaActual;
		}

		public List<PageItem> getPaginas() {
			return paginas;
		}
		
		//METODOS
		
		public boolean isFirst() {
			return page.isFirst();
		}
		
		public boolean isLast() {
			return page.isLast();
		}
		
		public boolean isHasNext() {
			return page.hasNext();
		}
		
		public boolean isHasPrevious() {
			return page.hasPrevious();
		}
		
	
}
