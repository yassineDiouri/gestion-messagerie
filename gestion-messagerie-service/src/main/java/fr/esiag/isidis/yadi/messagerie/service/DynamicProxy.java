package fr.esiag.isidis.yadi.messagerie.service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import fr.esiag.isidis.yadi.messagerie.metier.IManagerMetier;
import fr.esiag.isidis.yadi.messagerie.model.Contact;
import fr.esiag.isidis.yadi.messagerie.model.Message;

/**
 * 
 * @author Yassine Diouri
 *
 */
public class DynamicProxy implements InvocationHandler {
	
	private List<Pair<String, String>> replicants = new ArrayList<Pair<String, String>>();
	private Object proxy;
	
	public static Object newInstance(Object obj) {
        return java.lang.reflect.Proxy.newProxyInstance(
            obj.getClass().getClassLoader(),
            obj.getClass().getInterfaces(),
            new DynamicProxy(obj));
    }
	
	public DynamicProxy(Object object) {
		super();
		this.proxy = object;
		replicants.add(Pair.of("127.0.0.1", "2500"));
		replicants.add(Pair.of("127.0.0.1", "2600"));
		replicants.add(Pair.of("127.0.0.1", "2700"));
	}
	
	public Object invoke(Object _proxy, Method method, Object[] args) throws Throwable {
		Object result = null;
		
		Class<?> declaringClass = method.getDeclaringClass();
		
		if (declaringClass.isAssignableFrom(proxy.getClass().getInterfaces()[0])) {
			doReplicate(method, args);
			result = method.invoke(proxy, args);
		}
		return result;
	}

	private void doReplicate(Method method, Object[] args) {
		String name = method.getName();
		
		switch(name){
		
		case "inscription":
			for(Pair<String, String> p : replicants){
				try {
					Registry reg = LocateRegistry.getRegistry(Integer.parseInt(p.getRight())+1);
					IManagerMetier metier = (IManagerMetier) reg.lookup("metier");
					metier.inscription((Contact) args[0]);
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (RemoteException e) {
					e.printStackTrace();
				} catch (NotBoundException e) {
					e.printStackTrace();
				}
			}
			break;
		
		case "envoieMSG":
			for(Pair<String, String> p : replicants){
				try {
					Registry reg = LocateRegistry.getRegistry(Integer.parseInt(p.getRight())+1);
					IManagerMetier metier = (IManagerMetier) reg.lookup("metier");
					metier.envoieMSG((Message) args[0]);
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (RemoteException e) {
					e.printStackTrace();
				} catch (NotBoundException e) {
					e.printStackTrace();
				}
			}
			break;
		}
	}
}
