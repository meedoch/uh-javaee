package tn.undefined.universalhaven.buisness;

import java.util.List;
import java.util.Set;

import javax.ejb.Local;

import tn.undefined.universalhaven.entity.Camp;
import tn.undefined.universalhaven.entity.Resource;

@Local
public interface ResourceServiceLocal {
	public boolean addResource(Resource resource);

	public boolean addResourceToCamp(Resource resource,int campId);

	public Boolean depositResource(Resource resource);

	public Set<Resource> getCampResources(long idCamp);

	public boolean withdrawResource(Resource resource, Double qte);

	public Boolean removeResource(Resource resource);
}
