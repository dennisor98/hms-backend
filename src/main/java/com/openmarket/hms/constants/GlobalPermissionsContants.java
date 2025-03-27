package com.openmarket.hms.constants;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


public class GlobalPermissionsContants {
	public static class CreateUser extends PermissionEntry {
		public final static String PERMISSION = "can.create.user";
		public final static String DESCRIPTION = "Create User";
		public final static String CATEGORY = "user";
		@Override
		public String getPERMISSION() {
			return PERMISSION;
		}

		@Override
		public String getDESCRIPTION() {
			return DESCRIPTION;
		}

		@Override
		public String getCATEGORY() {
			return CATEGORY;
		}

	}
	
	public static class EditUser extends PermissionEntry {
		public final static String PERMISSION = "can.edit.user";
		public final static String DESCRIPTION = "Edit User";
		public final static String CATEGORY = "user";
		@Override
		public String getPERMISSION() {
			return PERMISSION;
		}

		@Override
		public String getDESCRIPTION() {
			return DESCRIPTION;
		}

		@Override
		public String getCATEGORY() {
			return CATEGORY;
		}

	}
	
	
	public static class GetUsers extends PermissionEntry {
		public final static String PERMISSION = "can.get.users";
		public final static String DESCRIPTION = "Get Users";
		public final static String CATEGORY = "user";
		@Override
		public String getPERMISSION() {
			return PERMISSION;
		}

		@Override
		public String getDESCRIPTION() {
			return DESCRIPTION;
		}

		@Override
		public String getCATEGORY() {
			return CATEGORY;
		}

	}
	
	public static class DeleteUser extends PermissionEntry {
		public final static String PERMISSION = "can.delete.user";
		public final static String DESCRIPTION = "Delete User";
		public final static String CATEGORY = "user";
		@Override
		public String getPERMISSION() {
			return PERMISSION;
		}

		@Override
		public String getDESCRIPTION() {
			return DESCRIPTION;
		}

		@Override
		public String getCATEGORY() {
			return CATEGORY;
		}
	}
	
	public static class CreateRole extends PermissionEntry {
		public final static String PERMISSION = "can.create.role";
		public final static String DESCRIPTION = "Create role";
		public final static String CATEGORY ="role";
		@Override
		public String getPERMISSION() {
			return PERMISSION;
		}

		@Override
		public String getDESCRIPTION() {
			return DESCRIPTION;
		}

		@Override
		public String getCATEGORY() {
			return CATEGORY;
		}

	}
	
	public static class GetRole extends PermissionEntry {
		public final static String PERMISSION = "can.get.role";
		public final static String DESCRIPTION = "Get role";
		public final static String CATEGORY = "role";
		@Override
		public String getPERMISSION() {
			return PERMISSION;
		}

		@Override
		public String getDESCRIPTION() {
			return DESCRIPTION;
		}

		@Override
		public String getCATEGORY() {
			return CATEGORY;
		}
	}
	
	public static class EditRole extends PermissionEntry {
		public final static String PERMISSION = "can.edit.role";
		public final static String DESCRIPTION = "Edit role";
		public final static String CATEGORY = "role";
		@Override
		public String getPERMISSION() {
			return PERMISSION;
		}

		@Override
		public String getDESCRIPTION() {
			return DESCRIPTION;
		}

		@Override
		public String getCATEGORY() {
			return CATEGORY;
		}

	}
	
	
	
	public static class DeleteRole extends PermissionEntry {
		public final static String PERMISSION = "can.delete.role";
		public final static String DESCRIPTION = "Delete role";
		public final static String CATEGORY = "role";
		@Override
		public String getPERMISSION() {
			return PERMISSION;
		}

		@Override
		public String getDESCRIPTION() {
			return DESCRIPTION;
		}

		@Override
		public String getCATEGORY() {
			return CATEGORY;
		}

	}
	
	public static class AssignRole extends PermissionEntry {
		public final static String PERMISSION = "can.assign.role";
		public final static String DESCRIPTION = "Assign role";
		public final static String CATEGORY = "role";
		@Override
		public String getPERMISSION() {
			return PERMISSION;
		}

		@Override
		public String getDESCRIPTION() {
			return DESCRIPTION;
		}

		@Override
		public String getCATEGORY() {
			return CATEGORY;
		}

	}
	
	public static class AssignPermission extends PermissionEntry {
		public final static String PERMISSION = "can.assign.permission";
		public final static String DESCRIPTION = "Assign permission";
		public final static String CATEGORY = "role";
		@Override
		public String getPERMISSION() {
			return PERMISSION;
		}

		@Override
		public String getDESCRIPTION() {
			return DESCRIPTION;
		}

		@Override
		public String getCATEGORY() {
			return CATEGORY;
		}

	}
	
	public static class RevokePermission extends PermissionEntry {
		public final static String PERMISSION = "can.create.role";
		public final static String DESCRIPTION = "Create role";
		public final static String CATEGORY = "role";
		@Override
		public String getPERMISSION() {
			return PERMISSION;
		}

		@Override
		public String getDESCRIPTION() {
			return DESCRIPTION;
		}

		@Override
		public String getCATEGORY() {
			return CATEGORY;
		}

	}
	
	
	
	public static class CreatePatient extends PermissionEntry {
		public final static String PERMISSION = "can.create.patient";
		public final static String DESCRIPTION = "Create patient";
		public final static String CATEGORY = "patient";
		@Override
		public String getPERMISSION() {
			return PERMISSION;
		}

		@Override
		public String getDESCRIPTION() {
			return DESCRIPTION;
		}

		@Override
		public String getCATEGORY() {
			return CATEGORY;
		}

	}
	
	public static class EditPatient extends PermissionEntry {
		public final static String PERMISSION = "can.edit.patient";
		public final static String DESCRIPTION = "Edit patient";
		public final static String CATEGORY = "patient";
		@Override
		public String getPERMISSION() {
			return PERMISSION;
		}

		@Override
		public String getDESCRIPTION() {
			return DESCRIPTION;
		}

		@Override
		public String getCATEGORY() {
			return CATEGORY;
		}
	}
	
	public static class ActivatePatient extends PermissionEntry {
		public final static String PERMISSION = "can.activate.patient";
		public final static String DESCRIPTION = "Activate patient";
		public final static String CATEGORY = "patient";
		@Override
		public String getPERMISSION() {
			return PERMISSION;
		}

		@Override
		public String getDESCRIPTION() {
			return DESCRIPTION;
		}

		@Override
		public String getCATEGORY() {
			return CATEGORY;
		}

	}
	
	public static class DeactivatePatient extends PermissionEntry {
		public final static String PERMISSION = "can.deactiate.patient";
		public final static String DESCRIPTION = "Deactivate patient";
		public final static String CATEGORY = "patient";
		@Override
		public String getPERMISSION() {
			return PERMISSION;
		}

		@Override
		public String getDESCRIPTION() {
			return DESCRIPTION;
		}

		@Override
		public String getCATEGORY() {
			return CATEGORY;
		}

	}
	
	
	public static class StartSession extends PermissionEntry {
		public final static String PERMISSION = "can.start.session";
		public final static String DESCRIPTION = "Start session";
		public final static String CATEGORY = "patient";
		@Override
		public String getPERMISSION() {
			return PERMISSION;
		}

		@Override
		public String getDESCRIPTION() {
			return DESCRIPTION;
		}

		@Override
		public String getCATEGORY() {
			return CATEGORY;
		}
	}
	
	
	public static class EndSession extends PermissionEntry {
		public final static String PERMISSION = "can.end.session";
		public final static String DESCRIPTION = "End session";
		public final static String CATEGORY = "session";
		@Override
		public String getPERMISSION() {
			return PERMISSION;
		}

		@Override
		public String getDESCRIPTION() {
			return DESCRIPTION;
		}

		@Override
		public String getCATEGORY() {
			return CATEGORY;
		}

	}
	
	public static class VoidSession extends PermissionEntry {
		public final static String PERMISSION = "can.void.session";
		public final static String DESCRIPTION = "Void session";
		public final static String CATEGORY = "session";
		@Override
		public String getPERMISSION() {
			return PERMISSION;
		}

		@Override
		public String getDESCRIPTION() {
			return DESCRIPTION;
		}

		@Override
		public String getCATEGORY() {
			return CATEGORY;
		}
	}
	
	public static Map<String, PermissionDetails> scan() {
	    Class<?>[] innerClasses = GlobalPermissionsContants.class.getDeclaredClasses();

	    Map<String, PermissionDetails> permissionDetailsMap = new HashMap<>();

	    for (Class<?> innerClass : innerClasses) {
	        // Check if the class extends PermissionEntry
	        if (PermissionEntry.class.isAssignableFrom(innerClass)) {
	            try {
	                // Create an instance of the inner class
	                PermissionEntry entryInstance = (PermissionEntry) innerClass.getDeclaredConstructor().newInstance();
	                
	                // Create PermissionDetails and add it to the map
	                PermissionDetails details = new PermissionDetails(
	                        entryInstance.getPERMISSION(),
	                        entryInstance.getDESCRIPTION(),
	                        entryInstance.getCATEGORY()
	                );
	                
	                permissionDetailsMap.put(entryInstance.getPERMISSION(), details);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    }

	    return permissionDetailsMap;
	}
		
}
