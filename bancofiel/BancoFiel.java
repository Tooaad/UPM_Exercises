package aed.bancofiel;

import java.util.Comparator;
import es.upm.aedlib.indexedlist.IndexedList;
import es.upm.aedlib.indexedlist.ArrayIndexedList;

/**
 * Implements the code for the bank application. Implements the client and the
 * "gestor" interfaces.
 */
public class BancoFiel implements ClienteBanco, GestorBanco {

	// NOTAD. No se deberia cambiar esta declaracion.
	public IndexedList<Cuenta> cuentas;

	// NOTAD. No se deberia cambiar esta constructor.
	public BancoFiel() {
		this.cuentas = new ArrayIndexedList<Cuenta>();
	}

	// ----------------------------------------------------------------------
	@Override
	public IndexedList<Cuenta> getCuentasOrdenadas(Comparator<Cuenta> cmp) {
		IndexedList<Cuenta> listaOrdenada = new ArrayIndexedList<Cuenta>((ArrayIndexedList<Cuenta>) cuentas);
		for (int i = 0; i < listaOrdenada.size(); i++) {
			for (int j = 0; j < listaOrdenada.size() - 1; j++) {
				if (cmp.compare(listaOrdenada.get(j), listaOrdenada.get(j + 1)) > 0) {
					Cuenta listaAux = listaOrdenada.get(j);
					listaOrdenada.removeElementAt(j);
					listaOrdenada.add((j + 1), listaAux);
				}
			}
		}
		return listaOrdenada;
	}

	@Override
	public String crearCuenta(String dni, int saldoInicial) {
		Cuenta cuenta = new Cuenta(dni, saldoInicial);
		insertar(cuenta, cuentas,
				// necesitamos un objeto comparator
				new Comparator<Cuenta>() {
					public int compare(Cuenta c1, Cuenta c2) {
						return c1.getId().compareTo(c2.getId());
					}
				});
		return cuenta.getId();
	}

	private static <E> void insertar(E e, IndexedList<E> list, Comparator<E> cmp) {
		int min = 0;
		int max = list.size() - 1;

		while (max >= min) {
			int mitad = (min + max) / 2;
			E otraE = list.get(mitad);
			int comparison = cmp.compare(e, otraE);
			if (comparison < 0)
				max = mitad - 1;
			else
				min = mitad + 1;
		}
		list.add(min, e);
	}

	private Cuenta getCuenta(String id) { // Funcion para obtener una cuenta a partir de un ID
		int i = 0;
		boolean existeCuenta = false;
		Cuenta cuenta = new Cuenta(null, 0);
		while (i < cuentas.size() && !existeCuenta) {
			existeCuenta = (id.equals(cuentas.get(i).getId()));
			i++;
		}
		i--; // Restamos -1 de la ultima iteracion del bucle para saber la posicion correcta
				// de la cuenta
		if (existeCuenta) {
			cuenta = cuentas.get(i);
			return cuenta;
		} else {
			return cuenta;
		}
	}

	@Override
	public void borrarCuenta(String id) throws CuentaNoExisteExc, CuentaNoVaciaExc {
		if (!(getCuenta(id).getSaldo() == 0)) {
			throw new CuentaNoVaciaExc();
		}
		if ((getCuenta(id).getId() != id)) {
			throw new CuentaNoExisteExc();
		} else {
			cuentas.remove(getCuenta(id));
		}
	}

	@Override
	public int ingresarDinero(String id, int cantidad) throws CuentaNoExisteExc {
		if ((getCuenta(id).getId() != id)) {
			throw new CuentaNoExisteExc();
		} else {
			getCuenta(id).ingresar(cantidad);
		}
		return getCuenta(id).getSaldo();
	}

	@Override
	public int retirarDinero(String id, int cantidad) throws CuentaNoExisteExc, InsuficienteSaldoExc {
		if ((getCuenta(id).getSaldo() <= 0) && getCuenta(id).getSaldo() >= cantidad) {
			throw new InsuficienteSaldoExc();
		}
		if ((getCuenta(id).getId() != id)) {
			throw new CuentaNoExisteExc();
		}
		getCuenta(id).retirar(cantidad);

		return getCuenta(id).getSaldo();
	}

	@Override
	public int consultarSaldo(String id) throws CuentaNoExisteExc {
		if ((getCuenta(id).getId() != id)) {
			throw new CuentaNoExisteExc();
		}

		return getCuenta(id).getSaldo();
	}

	@Override
	public void hacerTransferencia(String idFrom, String idTo, int cantidad)
			throws CuentaNoExisteExc, InsuficienteSaldoExc {
		if ((getCuenta(idFrom).getSaldo() <= 0) && getCuenta(idFrom).getSaldo() >= cantidad) {
			throw new InsuficienteSaldoExc();
		}
		if ((getCuenta(idFrom).getId() != idFrom)) {
			throw new CuentaNoExisteExc();
		}
		if ((getCuenta(idTo).getId() != idTo)) {
			throw new CuentaNoExisteExc();
		}
		getCuenta(idFrom).retirar(cantidad);
		getCuenta(idTo).ingresar(cantidad);
	}

	@Override
	public IndexedList<String> getIdCuentas(String dni) {
		IndexedList<String> lista2 = new ArrayIndexedList<String>();
		for (int i = 0; i < cuentas.size(); i++) {
			if ((cuentas.get(i).getDNI().equals(dni))) {
				lista2.add(lista2.size(), cuentas.get(i).getId());
			}
		}
		return lista2;
	}

	@Override
	public int getSaldoCuentas(String dni) {
		int saldoTotal = 0;
		for (int i = 0; i < cuentas.size(); i++) {
			if ((cuentas.get(i).getDNI().equals(dni))) {
				saldoTotal = saldoTotal + cuentas.get(i).getSaldo();
			}
		}
		return saldoTotal;
	}

	// ----------------------------------------------------------------------
	// NOTAD. No se deberia cambiar este metodo.
	public String toString() {
		return "banco";
	}

}
