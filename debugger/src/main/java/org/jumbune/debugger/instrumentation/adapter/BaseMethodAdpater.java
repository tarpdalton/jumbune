package org.jumbune.debugger.instrumentation.adapter;

import org.jumbune.common.yaml.config.Loader;
import org.jumbune.debugger.instrumentation.utils.InstrumentConstants;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.LineNumberNode;
import org.objectweb.asm.tree.MethodNode;


/**
 * This class is base for all method level adapters.
 * 
 */
public class BaseMethodAdpater extends MethodNode implements InstrumentConstants, Opcodes {
	private  Loader loader = null;
	private String clazzName;
	private String logClazzName;

	/**
	 * creates new instance for BaseMethodAdpater
	 * @param access
	 * @param name
	 * @param desc
	 * @param signature
	 * @param exceptions
	 * @param mv
	 */
	public BaseMethodAdpater(int access, String name, String desc, String signature, String[] exceptions, MethodVisitor mv) {
		super(ASM4, access, name, desc, signature, exceptions);
		this.mv = mv;
	}

	/**
	 * creates new instance for BaseMethodAdpater
	 * @param access
	 * @param name
	 * @param desc
	 * @param signature
	 * @param exceptions
	 * @param mv
	 * @param className
	 */
	public BaseMethodAdpater(int access, String name, String desc, String signature, String[] exceptions, MethodVisitor mv, String className) {
		super(ASM4, access, name, desc, signature, exceptions);
		this.mv = mv;
		this.clazzName = className;
	}

	/**
	 * creates new instance for BaseMethodAdpater
	 * @param loader
	 * @param access
	 * @param name
	 * @param desc
	 * @param signature
	 * @param exceptions
	 * @param mv
	 * @param className
	 * @param logClassName
	 */
	public BaseMethodAdpater(Loader loader, int access, String name, String desc, String signature, String[] exceptions, MethodVisitor mv,
			String className, String logClassName) {
		super(ASM4, access, name, desc, signature, exceptions);
		this.mv = mv;
		this.clazzName = className;
		this.logClazzName = logClassName;
		this.loader=loader;
	}

	/**
	 * method gets the class name
	 * @return
	 */
	public String getClassName() {
		return this.clazzName;
	}

	/**
	 * <p>
	 * This method gets the class name which will be logged in the log files
	 * </p>
	 * 
	 * @return String Class name to be logged in class name
	 */
	public String getLogClazzName() {
		return this.logClazzName;
	}

	/**
	 * <p>
	 * Gets the previous line number for the given node
	 * </p>
	 * 
	 * @param ain
	 *            node
	 * @return int line number
	 */
	public int getPreviousLineNumber(AbstractInsnNode ain) {
		AbstractInsnNode lineNode = ain.getPrevious();
		while (!(lineNode instanceof LineNumberNode)) {
			lineNode = lineNode.getPrevious();
		}
		return ((LineNumberNode) lineNode).line;
	}

	/**
	 * <p>
	 * Gets the next line number for the given node
	 * </p>
	 * 
	 * @param ain
	 *            node
	 * @return int line number
	 */
	public int getNextLineNumber(AbstractInsnNode ain) {
		AbstractInsnNode lineNode = ain.getNext();
		while (!(lineNode instanceof LineNumberNode)) {
			lineNode = lineNode.getNext();
		}
		return ((LineNumberNode) lineNode).line;
	}

	/**
	 * <p>
	 * Gets the previous line number node for the given node
	 * </p>
	 * 
	 * @param ain
	 *            node
	 * @return ain line number node
	 */
	public AbstractInsnNode getPreviousLineNode(AbstractInsnNode ain) {
		AbstractInsnNode lineNode = ain.getPrevious();
		while (!(lineNode instanceof LineNumberNode)) {
			lineNode = lineNode.getPrevious();
		}
		return lineNode;
	}

	/**
	 * <p>
	 * Gets the next line number node for the given node
	 * </p>
	 * 
	 * @param ain
	 *            node
	 * @return ain line number node
	 */
	public AbstractInsnNode getNextLineNode(AbstractInsnNode ain) {
		AbstractInsnNode lineNode = ain.getNext();
		while (!(lineNode instanceof LineNumberNode)) {
			lineNode = lineNode.getNext();
		}
		return lineNode;
	}

	/**
	 * @param loader the loader to set
	 */
	public void setLoader(Loader loader) {
		this.loader = loader;
	}

	/**
	 * @return the loader
	 */
	protected Loader getLoader() {
		return loader;
	}
}
